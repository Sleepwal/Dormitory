package com.sleepwalker.service;

import com.sleepwalker.entity.SystemAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sleepwalker.form.UserForm;
import com.sleepwalker.vo.ResultVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
public interface SystemAdminService extends IService<SystemAdmin> {
    ResultVo login(UserForm userForm);
}
