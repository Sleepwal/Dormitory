package com.sleepwalker.service;

import com.sleepwalker.entity.Building;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sleepwalker.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
public interface BuildingService extends IService<Building> {
    PageVO list(Integer page, Integer size);
}
