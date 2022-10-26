package com.sleepwalker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sleepwalker.entity.Dormitory;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.service.DormitoryService;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.io.ResolverUtil;
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
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;

    @ApiOperation("分页查询所有宿舍")
    @GetMapping("/list/{page}/{size}")
    public ResultVo list(@PathVariable("page")Integer page, @PathVariable("size")Integer size) {
        return ResultVOUtil.success(dormitoryService.list(page, size));
    }

    @ApiOperation("模糊搜索")
    @GetMapping("/search")
    public ResultVo search(SearchForm searchForm) {
        return ResultVOUtil.success(dormitoryService.search(searchForm));
    }


    @ApiOperation("添加宿舍")
    @PostMapping("/save")
    public ResultVo save(@RequestBody Dormitory dormitory) {
        dormitory.setAvailable(dormitory.getType());
        if(dormitoryService.save(dormitory))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }

    @ApiOperation("查询有余量的宿舍")
    @GetMapping("/availableList")
    public ResultVo availableList() {
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("available", 0);
        return ResultVOUtil.success(dormitoryService.list(queryWrapper));
    }

    @ApiOperation("根据id查询宿舍")
    @GetMapping("/findById/{id}")
    public ResultVo findById(@PathVariable("id")Integer id) {
        return ResultVOUtil.success(dormitoryService.getById(id));
    }

    @ApiOperation("更新宿舍")
    @PutMapping("/update")
    public ResultVo update(@RequestBody Dormitory dormitory) {
        if(dormitoryService.updateById(dormitory))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }

    @ApiOperation("删除宿舍")
    @DeleteMapping("/deleteById/{id}")
    public ResultVo deleteById(@PathVariable("id")Integer id) {
        if(dormitoryService.deleteById(id))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }
}

