package com.sleepwalker.form;

import lombok.Data;

/**
 * @package: com.sleepwalker.form
 * @className: StudentForm
 * @author: SleepWalker
 * @description: TODO
 * @date: 22:30
 * @version: 1.0
 */
@Data
public class StudentForm {
    private Integer id;

    private Integer number;

    private String name;

    private String gender;

    private Integer dormitoryId;

    private Integer oldDormitoryId;

    private String state = "入住";

    private String createDate;

}
