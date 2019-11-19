package com.hstc.gym.service;

import com.hstc.gym.entity.Reservation;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
public interface ReservationService {
    //1、通过课程详情编号did查询对应课程预约的学生
    public List<Reservation> findByDid(String did);

    //2、用户预约课程（新增）
    public boolean addReservation(Reservation reservation);

    //3、用户评价后 将预约状态该为“已评价”
    public boolean update(Integer reid);
}
