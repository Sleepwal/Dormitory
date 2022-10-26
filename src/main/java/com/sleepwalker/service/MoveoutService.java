package com.sleepwalker.service;

import com.sleepwalker.entity.Moveout;
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
public interface MoveoutService extends IService<Moveout> {
    public PageVO list(Integer page, Integer size);
    public PageVO search(SearchForm searchForm);
    Boolean moveout(Integer id, String value);
    PageVO moveoutList(Integer page, Integer size);
    public PageVO moveoutSearch(SearchForm searchForm);
}
