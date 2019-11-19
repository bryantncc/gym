package com.hstc.gym.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by yz on 2019/10/30.
 */
@Data
@TableName("tb_detail")
public class DetailBiao implements Serializable{
    @TableId(value = "did",type = IdType.AUTO)
    private Integer did;
    private Integer cid;
    private String ctime;
    private Integer tid;
    private Integer dnumber;
    private Integer rid;
}
