package com.hstc.gym.service;

import com.hstc.gym.entity.Student;

import java.util.List;

/**
 * Created by chen on 2019/10/30.
 */
public interface StudentService {

    //教练点击查看学生，返回对应课程的学生信息
    public List<Student> findByDid(Integer did);
}
