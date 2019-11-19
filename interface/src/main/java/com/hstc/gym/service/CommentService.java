package com.hstc.gym.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.gym.entity.Comment;
import com.hstc.gym.entity.Reservation;
import com.hstc.gym.entity.User;

import java.util.List;

/**
 * Created by Bryant on 2019/10/27.
 */
public interface CommentService {
    //1、用户评论课程（新增）
    public boolean addComment(Comment comment);

    //2、用户查看自己的评论
    public List<Comment> findAllComment();
}
