package com.sleepwalker.controller;


import com.sleepwalker.form.SearchForm;
import com.sleepwalker.service.MoveoutService;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/moveout")
public class MoveoutController {
    @Autowired
    private MoveoutService moveoutService;

    @ApiOperation("分页查询学生信息")
    @GetMapping("/list/{page}/{size}")
    public ResultVo list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return ResultVOUtil.success(moveoutService.list(page, size));
    }

    @ApiOperation("分页查询，模糊搜索")
    @GetMapping("/search")
    public ResultVo search(SearchForm searchForm) {
        return ResultVOUtil.success(moveoutService.search(searchForm));
    }

    @ApiOperation("查询所有迁出记录")
    @GetMapping("/moveoutList/{page}/{size}")
    public ResultVo moveoutList(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return ResultVOUtil.success(moveoutService.moveoutList(page, size));
    }

    @ApiOperation("学生迁入")
    @PutMapping("/moveout/{id}/{value}")
    public ResultVo moveout(@PathVariable("id")Integer id, @PathVariable("value")String value) {
        if(moveoutService.moveout(id, value))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }
}

