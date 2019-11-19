package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hstc.gym.dao.ReservationMapper;
import com.hstc.gym.entity.Reservation;
import com.hstc.gym.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
@Service(version = "1.0.0",interfaceClass =ReservationService.class)
//spring的service
@org.springframework.stereotype.Service
public class ReservationServiceImpl implements ReservationService {
    //注入dao
    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public List<Reservation> findByDid(String did) {
        QueryWrapper<Reservation> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("did",did);
        List<Reservation> list = reservationMapper.selectList(queryWrapper);
        return list;
    }


    @Override
    public boolean addReservation(Reservation reservation) {
        //获得用户预约日期
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        reservation.setRtime(formatter.format(date));
        int i = reservationMapper.insert(reservation);
        return i==1?true:false;
    }

    @Override
    public boolean update(Integer reid) {
        Reservation reservation = new Reservation();
        reservation.setReid(reid);
        reservation.setRstatus("已评价");
        int i = reservationMapper.updateById(reservation);
        return i==1?true:false;
    }
}
