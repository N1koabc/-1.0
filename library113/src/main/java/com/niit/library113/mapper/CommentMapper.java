package com.niit.library113.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.niit.library113.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    // MyBatis Plus 会自动实现增删改查，无需写代码
}