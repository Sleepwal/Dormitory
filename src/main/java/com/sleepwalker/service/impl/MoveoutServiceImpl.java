package com.sleepwalker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sleepwalker.entity.Dormitory;
import com.sleepwalker.entity.Moveout;
import com.sleepwalker.entity.Student;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.mapper.DormitoryMapper;
import com.sleepwalker.mapper.MoveoutMapper;
import com.sleepwalker.mapper.StudentMapper;
import com.sleepwalker.service.MoveoutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sleepwalker.util.CommonUtil;
import com.sleepwalker.vo.MoveoutVO;
import com.sleepwalker.vo.PageVO;
import com.sleepwalker.vo.StudentVO;
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
public class MoveoutServiceImpl extends ServiceImpl<MoveoutMapper, Moveout> implements MoveoutService {
    @Autowired
    private MoveoutMapper moveoutMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Student> studentPage = new Page<>(page, size);
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("state", "入住");
        Page<Student> resultPage = studentMapper.selectPage(studentPage, studentQueryWrapper);

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
        Page<Student> resultPage;
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("state", "入住");
        if(searchForm.getValue().equals("")) {  //搜索框为空
            resultPage = studentMapper.selectPage(studentPage, studentQueryWrapper);
        } else {
            studentQueryWrapper.like(searchForm.getKey(), searchForm.getValue());
            resultPage = studentMapper.selectPage(studentPage, studentQueryWrapper);
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
    public Boolean moveout(Integer id, String value) {
        //添加迁入记录
        Moveout moveout = new Moveout();
        moveout.setStudentId(id);
        moveout.setReason(value);
        moveout.setCreateDate(CommonUtil.createDate());
        Student student = studentMapper.selectById(id);
        moveout.setDormitoryId(student.getDormitoryId());
        if(moveoutMapper.insert(moveout) != 1)
            return false;

        //学生状态 --> 迁出
        student.setState("迁出");
        if(studentMapper.updateById(student) != 1)
            return false;

        //宿舍可住人数+1
        try{
            dormitoryMapper.addAvailable(student.getDormitoryId());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public PageVO moveoutList(Integer page, Integer size) {
        Page<Moveout> moveoutPage = new Page<>(page, size);
        Page<Moveout> resultPage = moveoutMapper.selectPage(moveoutPage, null);

        List<MoveoutVO> moveoutVOList = new ArrayList<>();
        for (Moveout moveout : resultPage.getRecords()) {
            MoveoutVO moveoutVO = new MoveoutVO();
            BeanUtils.copyProperties(moveout, moveoutVO);
            moveoutVO.setStudentName(studentMapper.selectById(moveout.getStudentId()).getName());
            moveoutVO.setDormitoryName(dormitoryMapper.selectById(moveout.getDormitoryId()).getName());
            moveoutVOList.add(moveoutVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(moveoutVOList);

        return pageVO;
    }

    @Override
    public PageVO moveoutSearch(SearchForm searchForm) {
        Page<Moveout> moveoutPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Moveout> resultPage = null;
        if(searchForm.getValue().equals("")) {
            resultPage = moveoutMapper.selectPage(moveoutPage, null);
        } else {
            QueryWrapper<Moveout> moveoutQueryWrapper = new QueryWrapper<>();
            if (searchForm.getKey().equals("studentName")) {
                //模糊搜索学生名字
                QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
                studentQueryWrapper.like("name", searchForm.getValue());
                List<Student> studentList = studentMapper.selectList(studentQueryWrapper);

                //搜索出的学生id
                List<Integer> studentIdList = new ArrayList<>();
                for (Student student : studentList) {
                    studentIdList.add(student.getId());
                }

                //student_id in studentIdList的学生
                moveoutQueryWrapper.in("student_id", studentIdList);
            } else if (searchForm.getKey().equals("dormitoryName")) {
                //模糊搜索宿舍名字
                QueryWrapper<Dormitory> dormitoryQueryWrapper = new QueryWrapper<>();
                dormitoryQueryWrapper.like("name", searchForm.getValue());
                List<Dormitory> dormitoryList = dormitoryMapper.selectList(dormitoryQueryWrapper);

                //搜索出的宿舍id
                List<Integer> dormitoryIdList = new ArrayList<>();
                for (Dormitory dormitory : dormitoryList) {
                    dormitoryIdList.add(dormitory.getId());
                }

                //in studentIdList的宿舍
                moveoutQueryWrapper.in("dormitory_id", dormitoryIdList);
            }
            resultPage = moveoutMapper.selectPage(moveoutPage, moveoutQueryWrapper);
        }
        List<MoveoutVO> moveoutVOList = new ArrayList<>();
        for (Moveout moveout : resultPage.getRecords()) {
            MoveoutVO moveoutVO = new MoveoutVO();
            BeanUtils.copyProperties(moveout, moveoutVO);
            moveoutVO.setStudentName(studentMapper.selectById(moveout.getStudentId()).getName());
            moveoutVO.setDormitoryName(dormitoryMapper.selectById(moveout.getDormitoryId()).getName());
            moveoutVOList.add(moveoutVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setTotal(resultPage.getTotal());
        pageVO.setData(moveoutVOList);

        return pageVO;
    }
}
