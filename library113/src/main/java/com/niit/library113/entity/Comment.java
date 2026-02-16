package com.niit.library113.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long newsId;
    private Long userId;
    private String content;
    private LocalDateTime createTime;

    // 这些字段数据库里没有，是查出来给前端显示的
    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String userAvatar;
}