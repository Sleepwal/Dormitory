package com.sleepwalker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sleepwalker.entity.Dormitory;
import com.sleepwalker.service.DormitoryService;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("查询有余量的宿舍")
    @GetMapping("/availableList")
    public ResultVo availableList() {
        QueryWrapper<Dormitory> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("available", 0);
        return ResultVOUtil.success(dormitoryService.list(queryWrapper));
    }
}

