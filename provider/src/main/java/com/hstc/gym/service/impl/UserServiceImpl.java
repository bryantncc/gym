package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hstc.gym.dao.UserMapper;
import com.hstc.gym.entity.User;
import com.hstc.gym.service.TrainerService;
import com.hstc.gym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Created by Bryant on 2019/10/28.
 */
@Service(version = "1.0.0",interfaceClass =UserService.class)
//spring的service
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    //注入dao
    @Autowired
    private UserMapper userMapper;
    //注入redis的模板
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    //1、显示所有用户信息
    @Override
    public List<User> findAllUser() {
        //使用前先用redis-server.exe redis.windows.conf命令行启动redis

        //记得再实体类实现序列化，跟dubbo一样
        System.out.println("从redis中读取数据");
        //从redis中读取数据,get里边的""是存到redis中的数据集的名称
        List<User> list = (List<User>) redisTemplate.opsForValue().get("allUsers");
        if (list==null){
            //从redis中拿不到数据，去sql数据库中取
            list = userMapper.selectList(null);
            System.out.println("从数据库读取数据存入redis中");
            //再存到redis中
            redisTemplate.opsForValue().set("allUsers",list);
        }
        return userMapper.selectList(null);
    }

    //2、用户登录
    @Override
    public User login(String uname, String upassword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uname",uname);
        queryWrapper.eq("upassword",upassword);
        return userMapper.selectOne(queryWrapper);
    }

    //3、通过用户编号和密码查找用户
    @Override
    public User findByUidAndUpassword(Integer uid, String oldpassword){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("upassword",oldpassword);
        return userMapper.selectOne(queryWrapper);
    }

    //4、用户修改密码
    @Override
    public boolean updatePwd(User user, String upassword) {
        user.setUpassword(upassword);
        int i = userMapper.updateById(user);
        return i==1?true:false;
    }

    //2、管理员删除用户
    @Override
    public boolean delu(Integer uid){
        int i= userMapper.deleteById(uid);
        return i==1?true:false;
    }

    //3、管理员修改用户信息
    @Override
    public User findByUid(Integer uid) {

        return userMapper.selectById(uid);
    }

    @Override
    public boolean updu(User user){
        System.out.print(user);
        int i=userMapper.updateById(user);
        return  i==1?true:false;
    }

    //4、管理员新增用户
    @Override
    public boolean adduser(User user){
        int i=userMapper.insert(user);
        return i==1?true:false;
    }

}
