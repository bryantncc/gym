package com.hstc.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by chen on 2019/10/30.
 */
@Data
@TableName("view_student")
public class Student implements Serializable {
    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;
    private Integer did;
    private String uname;
    private String uphone;
    private Integer reid;
    private String rtime;
}
