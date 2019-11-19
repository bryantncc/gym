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
@TableName("view_reservation")
public class View_Reservation implements Serializable {

    @TableId(value = "reid",type = IdType.AUTO)
    private Integer reid;
    private Integer uid;
    private String uname;
    private String rtime;
    private String ctime;
    private String cname;
    private String tname;
    private String rname;
    private Integer dnumber;
    private String rstatus;

}

