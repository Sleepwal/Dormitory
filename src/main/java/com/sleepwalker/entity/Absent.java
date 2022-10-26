package com.sleepwalker.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Absent implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer buildingId;

    private Integer dormitoryId;

    private Integer studentId;

    private Integer dormitoryAdminId;

    private String createDate;

    private String reason;


}
