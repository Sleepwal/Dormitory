package com.sleepwalker.form;

import lombok.Data;

/**
 * @package: com.sleepwalker.form
 * @className: searchForm
 * @author: SleepWalker
 * @description: TODO
 * @date: 15:51
 * @version: 1.0
 */
@Data
public class SearchForm {
    private String key;
    private String value;
    private Integer page;
    private Integer size;
}
