package com.sleepwalker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sleepwalker.entity.Dormitory;
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
}

