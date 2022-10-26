package com.sleepwalker.vo;

import lombok.Data;

/**
 * @package: com.sleepwalker.vo
 * @className: MoveoutVO
 * @author: SleepWalker
 * @description: TODO
 * @date: 12:08
 * @version: 1.0
 */
@Data
public class MoveoutVO {
    private Integer id;
    private String studentName;
    private String dormitoryName;
    private String reason;
    private String createDate;
}
