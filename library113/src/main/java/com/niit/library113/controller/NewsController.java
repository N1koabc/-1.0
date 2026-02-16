package com.niit.library113.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niit.library113.entity.News;
import com.niit.library113.service.NewsService;
import com.niit.library113.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    // 获取最新新闻（用于首页展示）
    @GetMapping("/latest")
    public ResponseEntity<?> getLatestNews() {
        // 按时间倒序取前6条
        List<News> list = newsService.list(new QueryWrapper<News>().orderByDesc("publish_date").last("LIMIT 6"));
        return ResponseEntity.ok(list);
    }

    // 管理员：发布新闻
    @PostMapping("/add")
    public ResponseEntity<?> addNews(@RequestHeader(value = "user-id") Long userId, @RequestBody News news) {
        if (!userService.isAdmin(userId)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("无权操作");

        news.setPublishDate(LocalDateTime.now());
        if(news.getCoverImage() == null || news.getCoverImage().isEmpty()) {
            // 默认给个图，防止布局崩坏
            news.setCoverImage("https://img.zcool.cn/community/01d90d5764ff650000012e7edb3052.jpg@1280w_1l_2o_100sh.jpg");
        }
        return newsService.save(news) ? ResponseEntity.ok("发布成功") : ResponseEntity.badRequest().body("发布失败");
    }

    // 管理员：删除新闻
    @PostMapping("/delete")
    public ResponseEntity<?> deleteNews(@RequestHeader(value = "user-id") Long userId, @RequestBody News news) {
        if (!userService.isAdmin(userId)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("无权操作");
        return newsService.removeById(news.getId()) ? ResponseEntity.ok("已删除") : ResponseEntity.badRequest().body("删除失败");
    }
}