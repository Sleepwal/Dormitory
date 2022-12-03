package com.sleepwalker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sleepwalker.entity.Absent;
import com.sleepwalker.entity.Dormitory;
import com.sleepwalker.entity.Student;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.service.AbsentService;
import com.sleepwalker.service.BuildingService;
import com.sleepwalker.service.DormitoryService;
import com.sleepwalker.service.StudentService;
import com.sleepwalker.util.RegexUtil;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sleepwalker.util.Regex.CHAR;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-26
 */
@RestController
@RequestMapping("/absent")
public class AbsentController {

    @Autowired
    private AbsentService absentService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private StudentService studentService;

    @ApiOperation("分页查询所有")
    @GetMapping("/list/{page}/{size}")
    public ResultVo list(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        return ResultVOUtil.success(this.absentService.list(page, size));
    }

    @ApiOperation("搜索，分页")
    @GetMapping("/search")
    public ResultVo search(SearchForm searchForm){
        return ResultVOUtil.success(this.absentService.search(searchForm));
    }
    @ApiOperation("查询所有宿舍楼")
    @GetMapping("/buildingList")
    public ResultVo buildingList(){
        return ResultVOUtil.success(this.buildingService.list());
    }

    @ApiOperation("根据id查询宿舍")
    @GetMapping("/findDormitoryByBuildingId/{id}")
    public ResultVo findDormitoryByBuildingId(@PathVariable("id") Integer id){
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("building_id", id);
        List<Dormitory> dormitoryList = this.dormitoryService.list(queryWrapper);
        return ResultVOUtil.success(dormitoryList);
    }

    @ApiOperation("根据id查询学生")
    @GetMapping("/findStudentByDormitoryId/{id}")
    public ResultVo findStudentByDormitoryId(@PathVariable("id") Integer id){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dormitory_id", id);
        List<Student> studentList = this.studentService.list(queryWrapper);
        return ResultVOUtil.success(studentList);
    }

    @ApiOperation("添加缺寝记录")
    @PostMapping("/save")
    public ResultVo save(@RequestBody Absent absent){
        String createDate = absent.getCreateDate();
//        createDate = createDate.substring(0, 10);
        createDate = RegexUtil.replaceRegex(CHAR, createDate, " ");
        absent.setCreateDate(createDate);
        boolean save = this.absentService.save(absent);
        if(!save) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);
    }
}

