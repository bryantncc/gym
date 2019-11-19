package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hstc.gym.dao.StudentMapper;
import com.hstc.gym.entity.Student;
import com.hstc.gym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chen on 2019/10/30.
 */
@Service(version = "1.0.0",interfaceClass =StudentService.class)
//spring的service
@org.springframework.stereotype.Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> findByDid(Integer did) {

        QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("did",did);
        return studentMapper.selectList(queryWrapper);
    }
}
