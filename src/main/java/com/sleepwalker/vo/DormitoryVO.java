package com.sleepwalker.vo;

import lombok.Data;

/**
 * @package: com.sleepwalker.vo
 * @className: DormitoryVO
 * @author: SleepWalker
 * @description: TODO
 * @date: 9:12
 * @version: 1.0
 */
@Data
public class DormitoryVO {
    private Integer id;
    private String buildingName;
    private String name;
    private Integer type;
    private Integer available;
    private String telephone;
}
