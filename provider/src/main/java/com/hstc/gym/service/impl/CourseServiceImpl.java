package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hstc.gym.dao.CourseMapper;
import com.hstc.gym.entity.Course;
import com.hstc.gym.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
@Service(version = "1.0.0",interfaceClass =CourseService.class)
//spring的service
@org.springframework.stereotype.Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    //1、显示所有的课程
    @Override
    public List<Course> findAllCourse() {
        return courseMapper.selectList(null);
    }

    //1、管理员查看所有课程信息
    /*@Override
    public List<Course> findAllCourse() {
        //使用前先用redis-server.exe redis.windows.conf命令行启动redis
        //记得再实体类实现序列化，跟dubbo一样
        System.out.println("从redis中读取数据");
        //从redis中读取数据,get里边的""是存到redis中的数据集的名称
        List<Course> list = (List<Course>) redisTemplate.opsForValue().get("allCourses");
        if (list==null){
            //从redis中拿不到数据，去sql数据库中取
            list=courseMapper.selectList(null);
            System.out.println("从数据库读取数据存入redis中");
            //再存到redis中
            redisTemplate.opsForValue().set("allCourses",list);

        }
        return courseMapper.selectList(null);
    }*/

    //2、管理员新增课程
    @Override
    public boolean addcourse(Course course){
        int i=courseMapper.insert(course);
        return i==1?true:false;
    }

    //3、管理员删除课程
    @Override
    public boolean delc(Integer cid){
        int i= courseMapper.deleteById(cid);
        return i==1?true:false;
    }
}
