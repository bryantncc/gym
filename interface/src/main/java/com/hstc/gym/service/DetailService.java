package com.hstc.gym.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.gym.entity.Detail;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
public interface DetailService {

    //1、显示所有课程信息
    public List<Detail> findAllDetail();

    //2、显示某一时间某一课程信息
    public List<Detail> findoneDetail(String cname);

    //3、用户预约后课程剩余预约人数-1
    public boolean reduceNum(Detail detail);

    //1、管理员查看所有课程信息
    public IPage<Detail> findByPage(Integer currPage, Integer pageSize);

    //2、管理员修改课程详情——1
    public Detail findByDid(Integer did);
}
