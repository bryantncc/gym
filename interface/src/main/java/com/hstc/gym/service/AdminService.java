package com.hstc.gym.service;

import com.hstc.gym.entity.Admin;

/**
 * Created by yz on 2019/10/24.
 */
public interface AdminService {

    //1.管理员登录
     public Admin auth1(String aphone, String apassword);
}
