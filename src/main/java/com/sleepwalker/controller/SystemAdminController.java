package com.sleepwalker.controller;


import com.sleepwalker.entity.DormitoryAdmin;
import com.sleepwalker.form.UserForm;
import com.sleepwalker.service.SystemAdminService;
import com.sleepwalker.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  系统管理员控制器
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {
    @Autowired
    private SystemAdminService systemAdminService;

    @GetMapping("/login")
    public ResultVo login(UserForm userForm) {
        return systemAdminService.login(userForm);
    }
}

