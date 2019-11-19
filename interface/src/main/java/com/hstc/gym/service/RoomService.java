package com.hstc.gym.service;

import com.hstc.gym.entity.Course;
import com.hstc.gym.entity.Room;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
public interface RoomService {

    //1、根据课程编号查询对应课程详情
    //public List<Course> findByLikeCid(String cid);
    //1、管理员查看所有教室信息
    public List<Room> findAllRoom();
}
