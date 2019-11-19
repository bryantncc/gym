package com.hstc.gym.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.gym.entity.Comment;
import com.hstc.gym.entity.Detail;
import com.hstc.gym.entity.User;
import com.hstc.gym.entity.View_Reservation;
import com.hstc.gym.service.CommentService;
import com.hstc.gym.service.DetailService;
import com.hstc.gym.service.ReservationService;
import com.hstc.gym.service.View_ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/10/27.
 */

@RestController
public class CommentController {

        @Reference(version = "1.0.0")
        private CommentService commentService;
        @Reference(version = "1.0.0")
        private View_ReservationService view_reservationService;
        @Reference(version = "1.0.0")
        private ReservationService reservationService;

        //用户对课程进行评价
        @PostMapping("comment")
        public ModelAndView reduceNum(Comment comment){
                ModelAndView mav = new ModelAndView();
                boolean isok = commentService.addComment(comment);
                if(isok){
                        //将预约状态该为“已评价”
                        boolean isok1 = reservationService.update(comment.getReid());
                        mav.setViewName("success");
                        mav.addObject("msg","发表成功！即将跳转至首页。。");
                        mav.addObject("url", "index");
                }
                else{
                        mav.setViewName("fail");
                        mav.addObject("msg","发表失败！请重新操作！");
                }
                return mav;
        }

        //用户查看自己的评价
        @GetMapping("usercomment")
        public ModelAndView userComment(HttpSession session, Integer pageNum, Integer pageSize){
                if (pageNum == null)
                        pageNum = 1;
                if (pageSize == null)
                        pageSize = 5;
                ModelAndView mav = new ModelAndView();
                User user = (User) session.getAttribute("user");
                List<Comment> list = commentService.findAllComment();
                IPage<View_Reservation> iPage = view_reservationService.findByUid(user.getUid(),pageNum,pageSize);
                mav.addObject("list",list);
                mav.addObject("iPage",iPage);
                mav.setViewName("usercomment");
                return mav;
        }



}
