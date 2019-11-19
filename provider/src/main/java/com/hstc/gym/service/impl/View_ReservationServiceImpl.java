package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hstc.gym.dao.ReservationMapper;
import com.hstc.gym.dao.View_ReservationMapper;
import com.hstc.gym.entity.View_Reservation;
import com.hstc.gym.service.View_ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
@Service(version = "1.0.0",interfaceClass =View_ReservationService.class)
//spring的service
@org.springframework.stereotype.Service
public class View_ReservationServiceImpl implements View_ReservationService {
    //注入dao
    @Autowired
    private View_ReservationMapper view_reservationMapper;

    @Override
    public IPage<View_Reservation> findByUid(Integer uid, Integer pageNum, Integer pageSize) {
        QueryWrapper<View_Reservation> view_reservationQueryWrapper = new QueryWrapper<>();
        view_reservationQueryWrapper.eq("uid",uid);
        return view_reservationMapper.selectPage(new Page<>(pageNum,pageSize),view_reservationQueryWrapper);
    }

    @Override
    public View_Reservation findByReid(Integer reid) {
        return view_reservationMapper.selectById(reid);
    }
}
