package com.hstc.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Bryant on 2019/10/27.
 */
@Data
@TableName("tb_reservation")
public class Reservation implements Serializable {

    @TableId(value = "reid",type = IdType.AUTO)
    private Integer reid;
    private Integer uid;
    private Integer did;
    private String rtime;
    private String rstatus;
}

