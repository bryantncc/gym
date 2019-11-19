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
@TableName("tb_courses")
public class Course implements Serializable {
    @TableId(value = "cid",type = IdType.AUTO)
    private Integer cid;
    private String cname;
    private String cmain;
}
