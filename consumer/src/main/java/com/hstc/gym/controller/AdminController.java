package com.hstc.gym.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hstc.gym.entity.Admin;
import com.hstc.gym.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * Created by yz on 2019/10/24.
 */
@Controller
public class AdminController {

    @Reference(version = "1.1.0")
    private AdminService adminService;

    @GetMapping("aindex")
    public ModelAndView aindex(){

        ModelAndView mav=new ModelAndView();
        mav.setViewName("aindex");
        return mav;
    }

    //1、管理员登录
    @PostMapping("auth1")
    public ModelAndView auth1(String aphone, String apassword, HttpSession session){
        ModelAndView mav=new ModelAndView();
        Admin admin=adminService.auth1(aphone,apassword);
        if (admin!=null){
            session.setAttribute("admin",admin);
            mav.setViewName("home");
        }else {
            mav.setViewName("fail");
            mav.addObject("msg","登录失败");
        }
        return mav;
    }
}
