package com.sleepwalker.controller;


import com.sleepwalker.entity.Student;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.form.StudentForm;
import com.sleepwalker.service.StudentService;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * @param student:
     * @return ResultVo
     * @author SleepWalker
     * @description 添加一个学生
     */
    @ApiOperation("添加学生")
    @PostMapping("/save")
    public ResultVo save(@RequestBody Student student) {
        if(studentService.mySave(student)) return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }

    /**
     * @param page:
     * @param size:
     * @return ResultVo
     * @author SleepWalker
     * @description 分页查询学生信息
     */
    @ApiOperation("分页查询学生信息")
    @GetMapping("/list/{page}/{size}")
    public ResultVo list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return ResultVOUtil.success(studentService.list(page, size));
    }

    /**
     * @param searchForm:
     * @return ResultVo
     * @author SleepWalker
     * @description 分页查询，模糊搜索
     */
    @ApiOperation("分页查询，模糊搜索")
    @GetMapping("/search")
    public ResultVo search(SearchForm searchForm) {
        return ResultVOUtil.success(studentService.search(searchForm));
    }

    /**
     * @param id:
     * @return ResultVo
     * @author SleepWalker
     * @description 根据id查询学生,返回值加上旧宿舍id
     */
    @ApiOperation("根据id查询学生,返回值加上旧宿舍id")
    @GetMapping("/findById/{id}")
    public ResultVo findById(@PathVariable("id") Integer id) {
        Student student = studentService.getById(id);
        StudentForm studentForm = new StudentForm();
        BeanUtils.copyProperties(student, studentForm);
        studentForm.setOldDormitoryId(student.getDormitoryId());

        return ResultVOUtil.success(studentForm);
    }

    /**
     * @param studentForm:
     * @return ResultVo
     * @author SleepWalker
     * @description 更新学生信息
     */
    @ApiOperation("更新学生信息")
    @PutMapping("/update")
    public ResultVo update(@RequestBody StudentForm studentForm) {
        if(studentService.update(studentForm))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }

    /**
     * @param id:
     * @return ResultVo
     * @author SleepWalker
     * @description 根据id删除学生
     */
    @ApiOperation("删除学生")
    @DeleteMapping("/deleteById/{id}")
    public ResultVo deleteById(@PathVariable("id") Integer id) {
        if(studentService.delete(id))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }
}

