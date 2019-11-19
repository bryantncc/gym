package com.hstc.gym.service;

import com.hstc.gym.entity.User;

import java.util.List;

/**
 * Created by Bryant on 2019/10/28.
 */
public interface UserService {

    //1、显示所有用户信息
    public List<User> findAllUser();

    //2、用户登录
    public User login(String uname, String upassword);

    //3、通过用户编号和密码查找用户
    public User findByUidAndUpassword(Integer uid, String oldpassword);

    //4、用户修改密码
    public boolean updatePwd(User user, String upassword);

    /*//1、管理员查看所有用户信息
    public List<User> findAllUser();*/

    //2、管理员删除用户
    public boolean delu(Integer uid);

    //3、管理员修改用户信息
    public User findByUid(Integer uid);
    public boolean updu(User user);

    //4、管理员新增用户
    public boolean adduser(User user);
}
