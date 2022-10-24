package com.sleepwalker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sleepwalker.entity.Dormitory;
import com.sleepwalker.entity.Student;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.form.StudentForm;
import com.sleepwalker.mapper.DormitoryMapper;
import com.sleepwalker.mapper.StudentMapper;
import com.sleepwalker.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sleepwalker.util.CommonUtil;
import com.sleepwalker.vo.PageVO;
import com.sleepwalker.vo.StudentVO;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Override
    public Boolean mySave(Student student) {
        //添加学生
        student.setCreateDate(CommonUtil.createDate());
        int save = studentMapper.insert(student);
        if(save != 1)
            return false;

        //宿舍可住人员-1
        Dormitory dormitory = dormitoryMapper.selectById(student.getDormitoryId());
        if(dormitory.getAvailable() == 0)
            return false;

        dormitory.setAvailable(dormitory.getAvailable() - 1);
        int update = dormitoryMapper.updateById(dormitory);
        if(update != 1)
            return false;

        return true;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Student> studentPage = new Page<>(page, size);
        Page<Student> resultPage = studentMapper.selectPage(studentPage, null);

        //转换成VO
        List<Student> studentList = resultPage.getRecords();
        List<StudentVO> studentVOList = new ArrayList<>();
        for(Student student : studentList) {
            StudentVO studentVO = new StudentVO();
            //复制相同属性
            BeanUtils.copyProperties(student, studentVO);
            studentVO.setDormitoryName(dormitoryMapper.selectById(student.getDormitoryId()).getName());
            studentVOList.add(studentVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Student> studentPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Student> resultPage = null;
        if(searchForm.getValue().equals("")) {  //搜索框为空
            resultPage = studentMapper.selectPage(studentPage, null);
        } else {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = studentMapper.selectPage(studentPage, queryWrapper);
        }

        //转换成VO
        List<Student> studentList = resultPage.getRecords();
        List<StudentVO> studentVOList = new ArrayList<>();
        for(Student student : studentList) {
            StudentVO studentVO = new StudentVO();
            //复制相同属性
            BeanUtils.copyProperties(student, studentVO);
            studentVO.setDormitoryName(dormitoryMapper.selectById(student.getDormitoryId()).getName());
            studentVOList.add(studentVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setData(studentVOList);
        pageVO.setTotal(resultPage.getTotal());
        return pageVO;
    }

    @Override
    public Boolean update(StudentForm studentForm) {
        //学生信息
        Student student = new Student();
        BeanUtils.copyProperties(studentForm, student);
        int update = studentMapper.updateById(student);
        if(update != 1)
            return false;

        //宿舍余量更改
        if(!studentForm.getDormitoryId().equals(studentForm.getOldDormitoryId())) {
            try {
                //原来宿舍余量+1
                dormitoryMapper.addAvailable(studentForm.getDormitoryId());
                //新宿舍余量+1
                dormitoryMapper.subAvailable(studentForm.getOldDormitoryId());
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean delete(Integer id) {
        //宿舍可住人数+1
        Student student = studentMapper.selectById(id);
        Dormitory dormitory = dormitoryMapper.selectById(student.getDormitoryId());
        if(dormitory.getType() > dormitory.getAvailable()) {
            if(dormitoryMapper.addAvailable(student.getDormitoryId()) != 1) {
                return false;
            }
        }

        //删除学生
        if(studentMapper.deleteById(id) != 1)
            return false;

        return true;
    }
}
