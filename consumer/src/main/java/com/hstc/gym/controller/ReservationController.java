package com.hstc.gym.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hstc.gym.entity.Detail;
import com.hstc.gym.entity.Reservation;
import com.hstc.gym.entity.User;
import com.hstc.gym.service.DetailService;
import com.hstc.gym.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/10/27.
 */

@RestController
public class ReservationController {

        @Reference(version = "1.0.0")
        private ReservationService reservationService;

        //用户预约课程（新增）
        @PostMapping("/reservation")
        public ModelAndView addReservation(Reservation reservation,Integer did, HttpSession session) {
                ModelAndView mav = new ModelAndView();
                User user = (User)session.getAttribute("user");
                reservation.setUid(user.getUid());
                System.out.println(did);
                System.out.println(reservation);
                boolean isok = reservationService.addReservation(reservation);
                return mav;
        }


}
