package com.sleepwalker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sleepwalker.entity.DormitoryAdmin;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.form.UserForm;
import com.sleepwalker.mapper.DormitoryAdminMapper;
import com.sleepwalker.service.DormitoryAdminService;
import com.sleepwalker.util.ResultVOUtil;
import com.sleepwalker.vo.PageVO;
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
public class DormitoryAdminServiceImpl extends ServiceImpl<DormitoryAdminMapper, DormitoryAdmin> implements DormitoryAdminService {
    @Autowired
    private DormitoryAdminMapper dormitoryAdminMapper;

    @Override
    public ResultVo register(DormitoryAdmin dormitoryAdmin) {
        QueryWrapper<DormitoryAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", dormitoryAdmin.getUsername());
        DormitoryAdmin one = getOne(wrapper);

        //用户已存在
        if(one != null) return ResultVOUtil.failWithCode(-2);

        if(save(dormitoryAdmin)) return ResultVOUtil.success(null);

        return ResultVOUtil.fail();
    }

    @Override
    public ResultVo login(UserForm userForm) {
        ResultVo<DormitoryAdmin> resultVo = new ResultVo<>();

        //1.用户是否存在
        QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userForm.getUsername());
        DormitoryAdmin dormitoryAdmin = dormitoryAdminMapper.selectOne(queryWrapper);
        if(dormitoryAdmin == null) {
            resultVo.setCode(-1);
        } else {
            //2.密码是否正确
            if(dormitoryAdmin.getPassword().equals(userForm.getPassword())) {
                resultVo.setCode(0);
                resultVo.setData(dormitoryAdmin); //设置表单数据
            } else {
                resultVo.setCode(-2);
            }
        }

        return resultVo;
    }

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<DormitoryAdmin> dormitoryAdminPage = new Page<>(page, size);
        Page<DormitoryAdmin> resultPage = dormitoryAdminMapper.selectPage(dormitoryAdminPage, null);
        PageVO pageVO = new PageVO();
        pageVO.setData(resultPage.getRecords());
        pageVO.setTotal(resultPage.getTotal());

        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<DormitoryAdmin> dormitoryAdminPage = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<DormitoryAdmin> resultPage = null;

        if(searchForm.getValue().equals("")) { //搜索为空
            resultPage = dormitoryAdminMapper.selectPage(dormitoryAdminPage, null);
        } else {
            QueryWrapper<DormitoryAdmin> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue()); //模糊搜索
            resultPage = dormitoryAdminMapper.selectPage(dormitoryAdminPage, queryWrapper);
        }

        PageVO pageVo = new PageVO();
        pageVo.setData(resultPage.getRecords());
        pageVo.setTotal(resultPage.getTotal());

        return pageVo;
    }
}
