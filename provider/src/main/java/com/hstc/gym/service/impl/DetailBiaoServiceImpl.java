package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hstc.gym.dao.DetailBiaoMapper;
import com.hstc.gym.entity.DetailBiao;
import com.hstc.gym.service.DetailBiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by yz on 2019/10/30.
 */
@Service(version = "1.0.0",interfaceClass = DetailBiaoService.class)
@org.springframework.stereotype.Service
public class DetailBiaoServiceImpl implements DetailBiaoService{
    //注入dao
    @Autowired
    private DetailBiaoMapper detailBiaoMapper;
    //注入redis的模板
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    //3、管理员修改课程详情——2

    @Override
    public boolean uptdb(DetailBiao detailBiao){
        int i=detailBiaoMapper.updateById(detailBiao);
        return  i==1?true:false;
    }

}
