package com.hstc.gym.controller;

import com.hstc.gym.entity.Course;
import com.hstc.gym.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2019/10/27.
 */

@RestController
public class CourseController {

        @Reference(version = "1.0.0")
        private CourseService courseService;

        //查看所有课程
        @GetMapping("course")
        public List<Course> allCourse(){
            return courseService.findAllCourse();
        }

        //1、管理员查看所有课程
        @GetMapping("allc")
        @ResponseBody
        public ModelAndView allCourse1(){
                List<Course> list = courseService.findAllCourse();
                ModelAndView mav=new ModelAndView();
                mav.addObject("listc",list);
                mav.setViewName("c");
                return mav;
        }

        //2、管理员新增课程
        @PostMapping("addcourse")
        public ModelAndView addcourse(Course course){
                boolean isok = courseService.addcourse(course);
                ModelAndView mav=new ModelAndView();
                System.out.print(isok);
                mav.setViewName("redirect:/allv");
                return  mav;
        }

        //3、管理员删除课程
        @GetMapping("delc")
        public ModelAndView delc( Integer cid){
                boolean b=courseService.delc(cid);
                System.out.print(b);
                ModelAndView mav=new ModelAndView();
                mav.setViewName("redirect:allv");
                return mav;
        }

}
