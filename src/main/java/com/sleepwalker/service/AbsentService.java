package com.sleepwalker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sleepwalker.entity.Absent;
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
public interface AbsentService extends IService<Absent> {
    public PageVO list(Integer page, Integer size);
    public PageVO search(SearchForm searchForm);
}
