package com.hstc.gym.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.gym.entity.*;
import com.hstc.gym.service.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by Administrator on 2019/10/27.
 */

@RestController
public class DetailController {

        @Reference(version = "1.0.0")
        private DetailService detailService;

        @Reference(version = "1.0.0")
        private CourseService courseService;

        @Reference(version = "1.0.0")
        private TrainerService trainerService;

        @Reference(version = "1.0.0")
        private RoomService roomService;

        @Reference(version = "1.0.0")
        private DetailBiaoService detailBiaoService;

        //1、管理员查看所有课程明细
        @GetMapping("allv")
        public ModelAndView allDetail(Integer page,Integer size){
                if(page==null)page=1;
                if (size==null)size=6;
                ModelAndView mav=new ModelAndView();
                mav.setViewName("c");
                IPage<Detail> iPage=detailService.findByPage(page,size);
                List<Course> clist=courseService.findAllCourse();
                List<Room> rlist=roomService.findAllRoom();
                Long pages=0L;
                Long counts=0L;
                if (iPage.getSize()==0L){
                        pages=0L;
                        counts=0L;
                }else {
                        pages=iPage.getTotal()/iPage.getSize();
                        counts=iPage.getCurrent();
                        if (iPage.getTotal()%iPage.getSize()!=0L) {
                                ++pages;
                        }
                }
                mav.addObject("clist",clist);
                mav.addObject("iPage",iPage);
                mav.addObject("pages",pages);

                mav.addObject("rrlist",rlist);
                mav.addObject("counts",counts);
                return mav;
        }

        //2、管理员修改课程明细——1
        @GetMapping("edtdetail")
        public ModelAndView edtdetail(Integer did){
                Detail detail =detailService.findByDid(did);
                List<Course> clist=courseService.findAllCourse();
                List<Trainer> tlist=trainerService.findAllTrainer();
                List<Room> rlist=roomService.findAllRoom();
                ModelAndView mav=new ModelAndView();
                mav.addObject("detail",detail);
                mav.addObject("clist",clist);
                mav.addObject("tlist",tlist);
                mav.addObject("rlist",rlist);
                mav.setViewName("upddatail");
                return mav;
        }
        //2、管理员提交修改的课程明细——2
        @PostMapping("uptdb")
        public ModelAndView uptdb(DetailBiao detailBiao){
                boolean b = detailBiaoService.uptdb(detailBiao);
                System.out.print(b);
                ModelAndView mav=new ModelAndView();
                mav.setViewName("redirect:/allv");
                return mav;
        }

        //查看所有课程
        @GetMapping("detail")
        public List<Detail> allDetail(){
            return detailService.findAllDetail();
        }

        //查看某一课程
        @GetMapping("onedetail")
        public ModelAndView oneDetail(String cname){
                ModelAndView mav = new ModelAndView();
                List<Detail> list = detailService.findoneDetail(cname);
                mav.addObject("list",list);
                return mav;
        }

        //用户预约后课程剩余预约人数-1
        @PostMapping("reduceNum")
        public void reduceNum(Detail detail){
                boolean isok = detailService.reduceNum(detail);
        }
}
