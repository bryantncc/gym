package com.hstc.gym.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hstc.gym.dao.CommentMapper;
import com.hstc.gym.dao.DetailMapper;
import com.hstc.gym.entity.Comment;
import com.hstc.gym.entity.Detail;
import com.hstc.gym.entity.User;
import com.hstc.gym.service.CommentService;
import com.hstc.gym.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
@Service(version = "1.0.0",interfaceClass =CommentService.class)
@org.springframework.stereotype.Service
public class CommentServiceImpl implements CommentService {

    //注入dao
    @Autowired
    private CommentMapper commentMapper;
    //注入redis的模板
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public boolean addComment(Comment comment) {
        //获得用户评论日期
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(comment);
        comment.setCreatetime(formatter.format(date));
        int i = commentMapper.insert(comment);
        return i==1?true:false;
    }

    @Override
        public List<Comment> findAllComment() {
        return commentMapper.selectList(null);
    }
}
