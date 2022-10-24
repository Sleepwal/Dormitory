package com.sleepwalker.controller;


import com.sleepwalker.entity.Building;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.mapper.BuildingMapper;
import com.sleepwalker.service.BuildingService;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    /**
     * @param building:
     * @return ResultVo
     * @author SleepWalker
     * @description 添加宿舍楼
     */
    @ApiOperation("添加宿舍楼")
    @PostMapping("/save")
    public ResultVo save(@RequestBody Building building) {
        if(buildingService.save(building))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }

    /**
     * @param page:
     * @param size:
     * @return ResultVo
     * @author SleepWalker
     * @description 分页查询所有宿舍楼
     */
    @ApiOperation("分页查询所有宿舍楼")
    @GetMapping("/list/{page}/{size}")
    public ResultVo list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return ResultVOUtil.success(buildingService.list(page, size));
    }

    @GetMapping("/search")
    public ResultVo search(SearchForm searchForm) {
        return ResultVOUtil.success(buildingService.search(searchForm));
    }


}

