package com.hstc.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Bryant on 2019/10/28.
 */
@Data
@TableName("tb_user")
public class User implements Serializable {
    @TableId(value = "uid",type = IdType.AUTO)
    private Integer uid;
    private String uname;
    private String uphone;
    private String upassword;
}
