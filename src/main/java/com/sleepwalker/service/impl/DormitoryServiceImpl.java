package com.sleepwalker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sleepwalker.entity.Dormitory;
import com.sleepwalker.entity.Student;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.mapper.BuildingMapper;
import com.sleepwalker.mapper.DormitoryMapper;
import com.sleepwalker.mapper.StudentMapper;
import com.sleepwalker.service.DormitoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sleepwalker.vo.DormitoryVO;
import com.sleepwalker.vo.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
@Service
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Dormitory> dormitoryPage = new Page<>(page, size);
        Page<Dormitory> resultPage = dormitoryMapper.selectPage(dormitoryPage, null);
        //转换成DormitoryVO
        List<DormitoryVO> dormitoryVOList = new ArrayList<>();
        for(Dormitory dormitory: resultPage.getRecords()) {
            DormitoryVO dormitoryVO = new DormitoryVO();
            BeanUtils.copyProperties(dormitory, dormitoryVO);
            dormitoryVO.setBuildingName(buildingMapper.selectById(dormitory.getBuildingId()).getName());
            dormitoryVOList.add(dormitoryVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setData(dormitoryVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Dormitory> dormitoryPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Dormitory> resultPage;
        if(searchForm.getValue().equals(""))
            resultPage = dormitoryMapper.selectPage(dormitoryPage, null);
        else {
            QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = dormitoryMapper.selectPage(dormitoryPage, queryWrapper);
        }

        //Building转化为BuildingVO
        List<DormitoryVO> dormitoryVOList = new ArrayList<>();
        for(Dormitory dormitory: resultPage.getRecords()) {
            DormitoryVO dormitoryVO = new DormitoryVO();
            BeanUtils.copyProperties(dormitory, dormitoryVO);
            dormitoryVO.setBuildingName(buildingMapper.selectById(dormitory.getBuildingId()).getName());
            dormitoryVOList.add(dormitoryVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setData(dormitoryVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public Boolean deleteById(Integer id) {
        //找到宿舍中的所有学生
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("dormitory_id", id);
        List<Student> studentList = studentMapper.selectList(studentQueryWrapper);

        for(Student student: studentList) {
            //未住满的宿舍id
            Integer availableDormitoryId = dormitoryMapper.findAvailableDormitoryId();
            student.setDormitoryId(availableDormitoryId);
            try{
                //给每个学生换宿舍
                studentMapper.updateById(student);
                //宿舍可住人数 -1
                dormitoryMapper.subAvailable(availableDormitoryId);
            } catch (Exception e) {
                return false;
            }
        }
        //删除宿舍
        if(dormitoryMapper.deleteById(id) != 1)
            return false;
        return true;
    }

}
