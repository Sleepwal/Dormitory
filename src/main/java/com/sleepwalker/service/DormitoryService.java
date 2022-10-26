package com.sleepwalker.service;

import com.sleepwalker.entity.Dormitory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
public interface DormitoryService extends IService<Dormitory> {
    PageVO list(Integer page, Integer size);
    public PageVO search(SearchForm searchForm);
}
