package com.hstc.gym.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.gym.entity.Reservation;
import com.hstc.gym.entity.User;
import com.hstc.gym.entity.View_Reservation;
import com.hstc.gym.service.ReservationService;
import com.hstc.gym.service.View_ReservationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/10/27.
 */

@RestController
public class View_ReservationController {

        @Reference(version = "1.0.0")
        private View_ReservationService view_reservationService;

        //用户查询自己的预约详情
        @GetMapping("userreserve")
        public ModelAndView addReservation(HttpSession session, Integer pageNum, Integer pageSize) {
                if (pageNum == null)
                        pageNum = 1;
                if (pageSize == null)
                        pageSize = 6;
                ModelAndView mav = new ModelAndView();
                User user = (User) session.getAttribute("user");
                IPage<View_Reservation> iPage = view_reservationService.findByUid(user.getUid(),pageNum, pageSize);
                mav.addObject("iPage", iPage);
                return mav;
        }

        //根据预约编号查找预约信息
        @PostMapping("onereserve/{reid}")
        public ModelAndView oneReserve(@PathVariable Integer reid) {
                ModelAndView mav = new ModelAndView();
                View_Reservation view_reservation = view_reservationService.findByReid(reid);
                mav.addObject("view_reservation", view_reservation);
                mav.setViewName("onereserve");
                return mav;
        }

}
