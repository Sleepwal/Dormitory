package com.sleepwalker.controller;


import com.sleepwalker.entity.DormitoryAdmin;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.form.UserForm;
import com.sleepwalker.service.DormitoryAdminService;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  宿管前端控制器
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/dormitoryAdmin")
public class DormitoryAdminController {
    @Autowired
    private DormitoryAdminService dormitoryAdminService;

    @ApiOperation("宿管注册")
    @PutMapping("/register")
    public ResultVo register(@RequestBody DormitoryAdmin dormitoryAdmin) {
        return dormitoryAdminService.register(dormitoryAdmin);
    }

    /**
     * @param userForm:
     * @return ResultVo
     * @author SleepWalker
     * @description 登录
     */
    @ApiOperation("宿管登录")
    @GetMapping("/login")
    public ResultVo login(UserForm userForm) {
        return dormitoryAdminService.login(userForm);
    }

    /**
     * 添加宿管
     * @param dormitoryAdmin
     * @return
     */
    @ApiOperation("添加宿管")
    @PostMapping("/save")
    public ResultVo save(@RequestBody DormitoryAdmin dormitoryAdmin) {
        boolean save = dormitoryAdminService.save(dormitoryAdmin);
        if(save)
            return ResultVOUtil.success(null);
        else
            return ResultVOUtil.fail();
    }

    /**
     * @param :
     * @return ResultVo
     * @author SleepWalker
     * @description 查询所有宿管
     */
    @ApiOperation("查询所有宿管")
    @GetMapping("/list")
    public ResultVo list() {
        return ResultVOUtil.success(dormitoryAdminService.list());
    }

   /**
    * @param page:
    * @param size:
    * @return ResultVo
    * @author SleepWalker
    * @description 分页查询宿管
    */
    @ApiOperation("分页查询宿管")
    @GetMapping("/list/{page}/{size}")
    public ResultVo list(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return ResultVOUtil.success(dormitoryAdminService.list(page, size));
    }

    /**
     * 根据字段模糊搜索
     * @param searchForm
     * @return ResultVo
     */
    @ApiOperation("根据字段模糊搜索")
    @GetMapping("/search")
    public ResultVo search(SearchForm searchForm) {
        return ResultVOUtil.success(dormitoryAdminService.search(searchForm));
    }

    /**
     * @param id:
     * @return ResultVo
     * @author SleepWalker
     * @description 根据id找宿管
     */
    @ApiOperation("根据id找宿管")
    @GetMapping("/findById/{id}")
    public ResultVo findById(@PathVariable Integer id) {
        return ResultVOUtil.success(dormitoryAdminService.getById(id));
    }

    /**
     * @param dormitoryAdmin:
     * @return ResultVo
     * @author SleepWalker
     * @description 修改宿管
     */
    @ApiOperation("修改宿管")
    @PutMapping("/update")
    public ResultVo update(@RequestBody DormitoryAdmin dormitoryAdmin) {
        boolean update = dormitoryAdminService.updateById(dormitoryAdmin);
        if(update) return ResultVOUtil.success(null);
        else return ResultVOUtil.fail();

    }

    /**
     * @param id:
     * @return ResultVo
     * @author SleepWalker
     * @description 删除宿管
     */
    @ApiOperation("删除宿管")
    @DeleteMapping("/deleteById/{id}")
    public ResultVo deleteById(@PathVariable Integer id) {
        if(dormitoryAdminService.removeById(id))
            return ResultVOUtil.success(null);
        return ResultVOUtil.fail();
    }
}

