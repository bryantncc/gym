package com.hstc.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yz on 2019/10/24.
 */
@Data
@TableName("tb_admin")
public class Admin implements Serializable{
    @TableId(value = "ano",type = IdType.AUTO)
    private Integer ano;
    private String aname;
    private String aphone;
    private String apassword;
}
