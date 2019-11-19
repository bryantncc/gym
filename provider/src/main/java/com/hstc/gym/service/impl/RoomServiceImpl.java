package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hstc.gym.dao.RoomMapper;

import com.hstc.gym.entity.Room;
import com.hstc.gym.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
@Service(version = "1.0.0",interfaceClass =RoomService.class)
@org.springframework.stereotype.Service
public class RoomServiceImpl implements RoomService {

    //注入dao
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    //1、管理员查看所有课程信息
    @Override
    public List<Room> findAllRoom() {
        //使用前先用redis-server.exe redis.windows.conf命令行启动redis
        //记得再实体类实现序列化，跟dubbo一样System.out.println("从redis中读取数据");
        //从redis中读取数据,get里边的""是存到redis中的数据集的名称
        List<Room> list = (List<Room>) redisTemplate.opsForValue().get("allCourses");
        if (list==null){
            //从redis中拿不到数据，去sql数据库中取
            list=roomMapper.selectList(null);
            System.out.println("从数据库读取数据存入redis中");
            //再存到redis中
            redisTemplate.opsForValue().set("allCourses",list);

        }
        return roomMapper.selectList(null);
    }


}
