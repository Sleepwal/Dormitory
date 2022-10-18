package com.sleepwalker.vo;

import lombok.Data;

/**
 * @package: com.sleepwalker.vo
 * @className: StudentVO
 * @author: SleepWalker
 * @description: TODO
 * @date: 23:11
 * @version: 1.0
 */
@Data
public class StudentVO {
    private Integer id;

    private Integer number;

    private String name;

    private String gender;

    private String dormitoryName;

    private String state;

    private String createDate;
}
