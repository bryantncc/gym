package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hstc.gym.dao.AdminMapper;
import com.hstc.gym.entity.Admin;
import com.hstc.gym.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by yz on 2019/10/24.
 */
//告诉它要实现的是哪个接口,或者说设置版本和接口类型
@Service(version = "1.1.0",interfaceClass =AdminService.class)
//spring的service
@org.springframework.stereotype.Service
public class AdminServiceImpl implements AdminService{

    //注入dao
    @Autowired
    private AdminMapper adminMapper;
    //注入redis的模板
   @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    //1、管理员登录
    @Override
    public Admin auth1(String aphone,String apassword){
        Admin admin =null;
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("aphone",aphone);
        queryWrapper.eq("apassword",apassword);
        admin=adminMapper.selectOne(queryWrapper);
        return admin;

    }

}
