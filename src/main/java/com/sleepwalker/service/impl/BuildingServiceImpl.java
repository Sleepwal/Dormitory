package com.sleepwalker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.sleepwalker.entity.Building;
import com.sleepwalker.entity.Dormitory;
import com.sleepwalker.entity.Student;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.mapper.BuildingMapper;
import com.sleepwalker.mapper.DormitoryAdminMapper;
import com.sleepwalker.mapper.DormitoryMapper;
import com.sleepwalker.mapper.StudentMapper;
import com.sleepwalker.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.BuildingVO;
import com.sleepwalker.vo.PageVO;
import org.apache.velocity.runtime.directive.Foreach;
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
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private DormitoryAdminMapper dormitoryAdminMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Building> page1 = new Page<>(page, size);
        Page<Building> buildingPage = buildingMapper.selectPage(page1, null);

        //Building转化为BuildingVO
        List<BuildingVO> list = new ArrayList<>();
        for(Building building: buildingPage.getRecords()) {
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building, buildingVO);
            buildingVO.setAdminName(dormitoryAdminMapper.selectById(building.getAdminId()).getName());
            list.add(buildingVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setTotal(buildingPage.getTotal());
        pageVO.setData(list);
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Building> page1 = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Building> buildingPage;
        if(searchForm.getValue().equals(""))
            buildingPage = buildingMapper.selectPage(page1, null);
        else {
            QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            buildingPage = buildingMapper.selectPage(page1, queryWrapper);
        }

        //Building转化为BuildingVO
        List<BuildingVO> list = new ArrayList<>();
        for(Building building: buildingPage.getRecords()) {
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building, buildingVO);
            buildingVO.setAdminName(dormitoryAdminMapper.selectById(building.getAdminId()).getName());
            list.add(buildingVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setTotal(buildingPage.getTotal());
        pageVO.setData(list);
        return pageVO;
    }

    @Override
    public Boolean deleteById(Integer id) {
        //找到宿舍楼的所有宿舍
        QueryWrapper<Dormitory> dormitoryQueryWrapper = new QueryWrapper<>();
        dormitoryQueryWrapper.eq("building_id", id);
        List<Dormitory> dormitoryList = dormitoryMapper.selectList(dormitoryQueryWrapper);

        for(Dormitory dormitory: dormitoryList) {
            //找到宿舍中的所有学生
            QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
            studentQueryWrapper.eq("dormitory_id", dormitory.getId());
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
            if(dormitoryMapper.deleteById(dormitory) != 1)
                return false;
        }

        //删除宿舍楼
        if(buildingMapper.deleteById(id) != 1)
            return false;

        return true;
    }
}
