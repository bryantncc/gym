package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hstc.gym.dao.DetailMapper;
import com.hstc.gym.entity.Detail;
import com.hstc.gym.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
@Service(version = "1.0.0",interfaceClass =DetailService.class)
@org.springframework.stereotype.Service
public class DetailServiceImpl implements DetailService {
    //注入dao
    @Autowired
    private DetailMapper detailMapper;
    //注入redis的模板
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public List<Detail> findAllDetail() {
        //使用前先用redis-server.exe redis.windows.conf命令行启动redis
        //记得再实体类实现序列化，跟dubbo一样
        System.out.println("从redis中读取数据");
        //从redis中读取数据,get里边的""是存到redis中的数据集的名称
        List<Detail> list = (List<Detail>) redisTemplate.opsForValue().get("allDetails");
        if (list==null){
            //从redis中拿不到数据，去sql数据库中取
            list = detailMapper.selectList(null);
            System.out.println("从数据库读取数据存入redis中");
            //再存到redis中
            redisTemplate.opsForValue().set("allDetails",list);
        }
        return detailMapper.selectList(null);
    }

    @Override
    public List<Detail> findoneDetail(String cname) {
        QueryWrapper<Detail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cname",cname);
        return detailMapper.selectList(queryWrapper);
    }

    @Override
    public boolean reduceNum(Detail detail) {
        detail.setDnumber(detail.getDnumber()-1);
        int i = detailMapper.updateById(detail);
        return i==1?true:false;
    }

    //1、管理员查看所有课程明细
    @Override
    public IPage<Detail> findByPage(Integer currPage, Integer pageSize) {
        return detailMapper.selectPage(new Page<>(currPage,pageSize),null);
    }

    //2、管理员修改课程明细——1
    @Override
    public Detail findByDid(Integer did) {

        return detailMapper.selectById(did);
    }


}
