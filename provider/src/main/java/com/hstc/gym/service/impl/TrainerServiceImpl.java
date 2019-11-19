package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hstc.gym.dao.TrainerMapper;
import com.hstc.gym.entity.Trainer;
import com.hstc.gym.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Created by Bryant on 2019/10/22.
 */
//告诉它要实现的是哪个接口,或者说设置版本和接口类型
@Service(version = "1.0.0",interfaceClass =TrainerService.class)
//spring的service
@org.springframework.stereotype.Service
public class TrainerServiceImpl implements TrainerService {


    //注入dao
    @Autowired
    private TrainerMapper trainerMapper;
    //注入redis的模板
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    //1、教练登录
    @Override
    public Trainer auth(String tphone, String tpassword) {
        //创造查询条件
        QueryWrapper<Trainer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tphone",tphone).eq("tpassword",tpassword);
        List<Trainer> list=trainerMapper.selectList(queryWrapper);

        // 如果list不为null且长度不为0
        if ((list != null) && list.size() > 0){
            // 返回list的第0个
            return list.get(0);
        }
        else{
            return null;
        }
    }

    //2、删除教练
    @Override
    public boolean delTrainer(Integer tid) {
        int i = trainerMapper.deleteById(tid);
        return i==1?true:false;
    }

    //3、更新教练信息
    @Override
    public boolean udtTrainer(Trainer trainer) {
        int i = trainerMapper.updateById(trainer);
        return i==1?true:false;
    }

    //4、传回单条教练信息
    @Override
    public Trainer findByTid(Integer tid) {
        return trainerMapper.selectById(tid);
    }

    //1、显示所有教练信息
    @Override
    public List<Trainer> findAllTrainer() {
        //使用前先用redis-server.exe redis.windows.conf命令行启动redis
        //记得再实体类实现序列化，跟dubbo一样
//        System.out.println("从redis中读取数据");
//        //从redis中读取数据,get里边的""是存到redis中的数据集的名称
//        List<Trainer> list = (List<Trainer>) redisTemplate.opsForValue().get("allTrainers");
//        if (list==null){
//            //从redis中拿不到数据，去sql数据库中取
//            list=trainerMapper.selectList(null);
//            System.out.println("从数据库读取数据存入redis中");
//            //再存到redis中
//            redisTemplate.opsForValue().set("allTrainers",list);
//
//        }
        return trainerMapper.selectList(null);
    }

    //2、管理员删除教练
    @Override
    public boolean delt(Integer tid){
        //System.out.println("进入删除");
        int i= trainerMapper.deleteById(tid);
        return i==1?true:false;
    }

    //3、管理员修改教练信息
    /*@Override
    public Trainer findByTid(Integer tid) {
        return trainerMapper.selectById(tid);
    }*/

    @Override
    public boolean updt(Trainer trainer){
        System.out.print("进入修改");
        int i=trainerMapper.updateById(trainer);
        return  i==1?true:false;
    }

    //4、管理员新增教练
    @Override
    public boolean addtrainer(Trainer trainer){
        int i=trainerMapper.insert(trainer);
        return i==1?true:false;
    }

}
