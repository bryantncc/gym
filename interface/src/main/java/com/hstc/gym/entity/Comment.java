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
@TableName("tb_comment")
public class Comment implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer reid;
    private String content;
    private String createtime;

}

