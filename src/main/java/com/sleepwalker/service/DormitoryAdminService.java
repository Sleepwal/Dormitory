package com.sleepwalker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sleepwalker.entity.DormitoryAdmin;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.form.UserForm;
import com.sleepwalker.vo.PageVO;
import com.sleepwalker.vo.ResultVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
public interface DormitoryAdminService extends IService<DormitoryAdmin> {
    public ResultVo register(DormitoryAdmin dormitoryAdmin);
    ResultVo login(UserForm userForm);
    PageVO list(Integer page, Integer size);
    PageVO search(SearchForm searchForm);
}
