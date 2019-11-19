package com.hstc.gym.service;

import com.hstc.gym.entity.Course;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
public interface CourseService {

    //1、显示所有课程
    public List<Course> findAllCourse();

    //2、管理员新增课程
    public boolean addcourse(Course course);

    //3、管理员删除课程
    public boolean delc(Integer cid);
}
