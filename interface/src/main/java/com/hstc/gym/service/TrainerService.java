package com.hstc.gym.service;

import com.hstc.gym.entity.Trainer;

import java.util.List;

/**
 * Created by Bryant on 2019/10/22.
 */
public interface TrainerService {

    //3、教练登录
    public Trainer auth(String tname, String tpassword);

    //4、注销教练账号
    public boolean delTrainer(Integer tid);

    //5、更新教练信息
    public boolean udtTrainer(Trainer trainer);

    //6、传回单条教练信息
    public Trainer findByTid(Integer tid);

    //1、管理员查看所有教练信息
    public List<Trainer> findAllTrainer();

    //2、管理员删除教练
    public boolean delt(Integer tid);

    //3、管理员修改教练信息
    /*public Trainer findByTid(Integer tid);*/
    public boolean updt(Trainer trainer);

    //4、管理员新增教练
    public boolean addtrainer(Trainer trainer);
}
