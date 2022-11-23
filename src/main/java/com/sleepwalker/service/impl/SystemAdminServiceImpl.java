package com.sleepwalker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sleepwalker.dto.SystemAdminDTO;
import com.sleepwalker.entity.SystemAdmin;
import com.sleepwalker.form.UserForm;
import com.sleepwalker.mapper.SystemAdminMapper;
import com.sleepwalker.service.SystemAdminService;
import com.sleepwalker.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sleepwalker.util.Constants.*;

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
        ResultVo<SystemAdminDTO> resultVo = new ResultVo<>();

        //1.用户是否存在
        QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userForm.getUsername());
        SystemAdmin systemAdmin = systemAdminMapper.selectOne(queryWrapper);
        if(systemAdmin == null) {
            resultVo.setCode(UserNotExit); //用户不存在
        } else {
            //2.密码是否正确
            if(systemAdmin.getPassword().equals(userForm.getPassword())) {
                //转化成DTO
                SystemAdminDTO systemAdminDTO = new SystemAdminDTO();
                BeanUtils.copyProperties(systemAdmin, systemAdminDTO);
                resultVo.setData(systemAdminDTO);  //设置表单数据
                resultVo.setCode(ResultSuccess);
            } else {
                resultVo.setCode(ErrorPassword);   //密码错误
            }
        }
        return resultVo;
    }
}
