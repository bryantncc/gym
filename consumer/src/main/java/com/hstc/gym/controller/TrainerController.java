package com.hstc.gym.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hstc.gym.entity.*;
import com.hstc.gym.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Bryant on 2019/10/22.
 */
@Controller
public class TrainerController {

    @Reference(version = "1.0.0")
    private TrainerService trainerService;

    @Reference(version = "1.0.0")
    private CourseService courseService;

    @Reference(version = "1.0.0")
    private RoomService roomService;

    @Reference(version = "1.0.0")
    private DetailService detailService;

    @Reference(version = "1.0.0")
    private ReservationService reservationService;
    @Reference(version = "1.0.0")
    private UserService userService;

    @Reference(version = "1.0.0")
    private StudentService studentService;


    //1、教练登录
    @GetMapping("auth")
    public ModelAndView auth(String uname, String upassword, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        Trainer trainer = trainerService.auth(uname, upassword);
        if (trainer == null) {
            // 验证失败
            mav.setViewName("fail");
            mav.addObject("msg", "登录失败，用户名或密码错误!");
        } else {
            // 设置显示用户名。。密码设置为空，不显示
            //trainer.setTpassword(null);
            // 通过session把user对象拿出来
            session.setAttribute("trainer", trainer);
            System.out.println(trainer);

            mav.setViewName("redirect:trainermain.html");
        }
        return mav;
    }

    //2、注销教练
    /*@DeleteMapping("delTrainer/{postid}")
    public ModelAndView delTrainer(@PathVariable Integer tid) {
        boolean b = trainerService.delTrainer(tid);
        System.out.println(b);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:../mypost");
        return mav;
    }*/





    //2、获取该教练信息
    @GetMapping("tData/{tid}")
    public ModelAndView findByComid(@PathVariable Integer tid) {
        //获得单个商品信息
        Trainer trainer = trainerService.findByTid(tid);
        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("trainer", trainer);
        mav.setViewName("tData");
        return mav;
    }



    //3、编辑教练信息
    @GetMapping("edtTrainer/{tid}")
    public ModelAndView edtTrainer(@PathVariable Integer tid) {
        //获得单个帖子信息
        Trainer trainer = trainerService.findByTid(tid);
        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("trainer", trainer);
        //跳到edtPost.html
        mav.setViewName("tEdit");
        return mav;
    }


    //4、更新教练信息
    @PutMapping("udtTrainer")
    public ModelAndView udtTrainer(Trainer trainer) {
        boolean isok = trainerService.udtTrainer(trainer);
        System.out.println(isok);
        ModelAndView mav = new ModelAndView();
        //跳转到post.do
        mav.setViewName("tData");
        return mav;
    }

    //5、显示所有课程
    @RequestMapping("allCourse")
    @ResponseBody
    public List<Course> findAllCourse() {
        return courseService.findAllCourse();
    }


    //6、显示所有我的课程-->详情
    @RequestMapping("allDetail")
    @ResponseBody
    public List<Detail> findAllDetail() {
        return detailService.findAllDetail();
    }


    //7、通过课程详情编号did查询对应课程预约的学生
    @GetMapping("student/{did}")
    @ResponseBody
    public ModelAndView findByDid(@PathVariable Integer did) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("tStudent");
        List<Student> list = studentService.findByDid(did);
        System.out.println(list);
        mav.addObject("list",list);
        return mav;
    }



    //8、显示所有的学生
    @RequestMapping("allUser")
    @ResponseBody
    public List<User> findAllUser() {
        return userService.findAllUser();
    }


    //主页查看所有教练信息
    @GetMapping("allTrainer")
    @ResponseBody
    public List<Trainer> Trainer(){
        return trainerService.findAllTrainer();
    }



    //直接跳转静态页面 -- 路由
    @RequestMapping("{url}.html")
    public ModelAndView pathRouter(@PathVariable String url) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(url);
        return mav;
    }

    //1、管理员查看所有教练
    @GetMapping("allt")
    @ResponseBody
    public ModelAndView allTrainer(){
        List<Trainer> list = trainerService.findAllTrainer();
        ModelAndView mav=new ModelAndView();
        mav.addObject("list",list);
        mav.setViewName("t");
        return mav;
    }

    //2、管理员删除教练
    @GetMapping("delt")
    public ModelAndView del( Integer tid){
        boolean b=trainerService.delt(tid);
        System.out.print(b);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("redirect:allt");
        return mav;
    }

    //3、管理员修改教练
    @GetMapping("edt")
    public ModelAndView edt(Integer tid){
        Trainer trainer = trainerService.findByTid(tid);
        ModelAndView mav=new ModelAndView();
        mav.addObject("trainer",trainer);
        mav.setViewName("updtrainer");
        return mav;
    }

    //保存教练信息
    @PutMapping("updt")
    public ModelAndView updt(Trainer trainer){
        boolean b = trainerService.updt(trainer);
        System.out.print(b);
        //ModelAndView mav=new ModelAndView();
        //mav.setViewName("redirect:../allt");
        return null;
    }

    //4、管理员新增教练

    @PostMapping("addtrainer")
    public ModelAndView addtrainer(Trainer trainer){
        boolean isok = trainerService.addtrainer(trainer);
        ModelAndView mav=new ModelAndView();
        System.out.print(isok);
        mav.setViewName("redirect:/allt");
        return  mav;
    }
}
