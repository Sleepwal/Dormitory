package com.sleepwalker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sleepwalker.entity.Building;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.mapper.BuildingMapper;
import com.sleepwalker.mapper.DormitoryAdminMapper;
import com.sleepwalker.service.BuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sleepwalker.vo.BuildingVO;
import com.sleepwalker.vo.PageVO;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {
    @Autowired
    private BuildingMapper buildingMapper;
    @Autowired
    private DormitoryAdminMapper dormitoryAdminMapper;

    @Override
    public PageVO list(Integer page, Integer size) {
        Page<Building> page1 = new Page<>(page, size);
        Page<Building> buildingPage = buildingMapper.selectPage(page1, null);

        //Building转化为BuildingVO
        List<BuildingVO> list = new ArrayList<>();
        for(Building building: buildingPage.getRecords()) {
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building, buildingVO);
            buildingVO.setAdminName(dormitoryAdminMapper.selectById(building.getAdminId()).getName());
            list.add(buildingVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setTotal(buildingPage.getTotal());
        pageVO.setData(list);
        return pageVO;
    }

    @Override
    public PageVO search(SearchForm searchForm) {
        Page<Building> page1 = new Page<>(searchForm.getPage(), searchForm.getSize());
        Page<Building> buildingPage;
        if(searchForm.getValue().equals(""))
            buildingPage = buildingMapper.selectPage(page1, null);
        else {
            QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
            queryWrapper.like(searchForm.getKey(), searchForm.getValue());
            buildingPage = buildingMapper.selectPage(page1, queryWrapper);
        }

        //Building转化为BuildingVO
        List<BuildingVO> list = new ArrayList<>();
        for(Building building: buildingPage.getRecords()) {
            BuildingVO buildingVO = new BuildingVO();
            BeanUtils.copyProperties(building, buildingVO);
            buildingVO.setAdminName(dormitoryAdminMapper.selectById(building.getAdminId()).getName());
            list.add(buildingVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setTotal(buildingPage.getTotal());
        pageVO.setData(list);
        return pageVO;
    }
}
