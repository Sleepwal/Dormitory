package com.sleepwalker.vo;

import lombok.Data;

/**
 * @package: com.sleepwalker.vo
 * @className: ResultVo
 * @author: SleepWalker
 * @description: 数据和状态码
 * @date: 15:57
 * @version: 1.0
 */
@Data
public class ResultVo<T> {
    private Integer code;
    private T data;
}
