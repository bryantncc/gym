package com.hstc.gym.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hstc.gym.entity.User;
import com.hstc.gym.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Bryant on 2019/10/22.
 */
@RestController
public class UserController {

   @Reference(version = "1.0.0")
   private UserService userService;

    //查看所有用户
    @GetMapping("user")
    public List<User> allUser(){
        return userService.findAllUser();
    }

    //根据用户身份选择登陆
    @PostMapping("user")
    public ModelAndView shenfen(String uname,String upassword,String shenfen){
        ModelAndView mav = new ModelAndView();
        System.out.println("uname"+uname);
        if (shenfen.equals("user")){
            mav.setViewName("redirect:user1");
        }
        if (shenfen.equals("trainer")){
            mav.setViewName("redirect:auth");
        }
        mav.addObject("uname",uname);
        mav.addObject("upassword",upassword);
        return mav;
    }

    //用户登录
    @GetMapping("user1")
    public ModelAndView userlogin(String uname, String upassword, HttpSession session){
        ModelAndView mav = new ModelAndView();
        User user = userService.login(uname,upassword);
        if(user!=null){
            session.setAttribute("user",user);
            mav.setViewName("redirect:index.html");
            /*mav.addObject("msg","登录成功！即将跳转至首页。。");
            mav.addObject("url", "index");*/
        }else{
            mav.setViewName("fail");
            mav.addObject("msg","登录失败！请重新登录！");
        }
        return mav;
  }

    //用户修改密码
    @PostMapping("updatepwd")
    public ModelAndView updatepwd(String upassword, String oldpassword, HttpSession session){
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute("user");
        //判断输入的原密码是否正确
        User oneuser = userService.findByUidAndUpassword(user.getUid(),oldpassword);
        if(oneuser!=null){
            boolean isok = userService.updatePwd(user,upassword);
            System.out.println(oldpassword);
            if(isok){
                mav.setViewName("success");
                mav.addObject("msg","修改成功！即将跳转至首页。。");
                mav.addObject("url", "index");
            }
            else{
                mav.setViewName("fail");
                mav.addObject("msg","修改失败！请重新操作！");
            }
        }else{
            mav.setViewName("fail");
            mav.addObject("msg","修改失败！请重新操作！");
        }
        return mav;
    }

    @RequestMapping("{url}")
    public ModelAndView pathRouter(@PathVariable String url) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(url);
        return mav;
    }

    //1、管理员查看所有用户
    @GetMapping("allu")
    public ModelAndView allUser1(){
        List<User> list = userService.findAllUser();
        ModelAndView mav=new ModelAndView();
        mav.addObject("listu",list);
        mav.setViewName("u");
        return mav;
    }

    //2、管理员删除用户
    @GetMapping("delu")
    public ModelAndView delu( Integer uid){
        boolean b=userService.delu(uid);
        System.out.print(b);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("redirect:allu");
        return mav;
    }

    //3、管理员修改用户信息
    @GetMapping("edu")
    public ModelAndView edu(Integer uid){
        User user = userService.findByUid(uid);
        ModelAndView mav=new ModelAndView();
        mav.addObject("user",user);
        mav.setViewName("upduser");
        return mav;
    }

    @PutMapping("updu")
    public boolean updu(User user){
        boolean b = userService.updu(user);
        System.out.print(b);
        //ModelAndView mav=new ModelAndView();
        //mav.setViewName("redirect:../allu");
        return b;
    }

//4、管理员新增用户

    @PostMapping("adduser")
    public ModelAndView adduser(User user){
        boolean isok = userService.adduser(user);
        ModelAndView mav=new ModelAndView();
        System.out.print(isok);
        mav.setViewName("redirect:/allu");
        return  mav;
    }
}
