package com.hstc.gym.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.gym.entity.Reservation;
import com.hstc.gym.entity.View_Reservation;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
public interface View_ReservationService {
    //1、用户查询自己的预约详情
    public IPage<View_Reservation> findByUid(Integer uid, Integer pageNum, Integer pageSize);

    //2、根据预约编号查找预约信息
    public View_Reservation findByReid(Integer reid);
}
