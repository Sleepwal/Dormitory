package com.sleepwalker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sleepwalker.entity.DormitoryAdmin;
import com.sleepwalker.entity.SystemAdmin;
import com.sleepwalker.form.UserForm;
import com.sleepwalker.mapper.SystemAdminMapper;
import com.sleepwalker.service.SystemAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sleepwalker.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
@Service
public class SystemAdminServiceImpl extends ServiceImpl<SystemAdminMapper, SystemAdmin> implements SystemAdminService {
    @Autowired
    private SystemAdminMapper systemAdminMapper;

    @Override
    public ResultVo login(UserForm userForm) {
        ResultVo<UserForm> resultVo = new ResultVo<>();

        //1.用户是否存在
        QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userForm.getUsername());
        SystemAdmin systemAdmin = systemAdminMapper.selectOne(queryWrapper);
        if(systemAdmin == null) {
            resultVo.setCode(-1);
        } else {
            //2.密码是否正确
            if(systemAdmin.getPassword().equals(userForm.getPassword())) {
                resultVo.setCode(0);
                resultVo.setData(userForm); //设置表单数据
            } else {
                resultVo.setCode(-2);
            }
        }
        return resultVo;
    }
}
