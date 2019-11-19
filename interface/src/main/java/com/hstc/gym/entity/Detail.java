package com.hstc.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bryant on 2019/10/27.
 */
@Data
@TableName("view_detail")
public class Detail implements Serializable {
    @TableId(value = "did",type = IdType.AUTO)
    private Integer did;
    private String cname;
    private String ctime;
    private String tname;
    private String rname;
    private Integer dnumber;
}
