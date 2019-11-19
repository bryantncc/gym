package com.hstc.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Bryant on 2019/10/22.
 */
@Data
@TableName("tb_trainer")
public class Trainer implements Serializable {
    @TableId(value = "tid",type = IdType.AUTO)
    private Integer tid;
    private String tname;
    private String tphone;
    private String tpassword;
    private String tintroduce;
    private String tphoto;
}
