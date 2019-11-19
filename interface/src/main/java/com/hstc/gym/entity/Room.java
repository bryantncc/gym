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
@TableName("tb_room")
public class Room implements Serializable {
    @TableId(value = "rid",type = IdType.AUTO)
    private Integer rid;
    private String rname;
}
