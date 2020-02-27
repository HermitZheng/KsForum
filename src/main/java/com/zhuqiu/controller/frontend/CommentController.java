package com.zhuqiu.controller.frontend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuqiu
 * @date 2020/2/25
 */

@Controller
@RequestMapping("/home/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;



    @ResponseBody
    @RequestMapping("/reply")
    public String insertSubmit(Comment comment, HttpSession session){
        System.out.println(comment);

        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        if (user == null){
            map.put("status", "login");
            map.put("msg", "请先登录！");
            return JSON.toJSONString(new JSONObject(map));
        }
        try {
            comment.setCommentCreateTime(new Date());
            commentService.insertComment(comment);
            articleService.updateCommentCount(comment.getArticleId());
            map.put("status", "success");
            map.put("msg", "回复成功！");
        } catch (RuntimeException e){
            map.put("status", "error");
            map.put("msg", "回复失败！");
        }finally {
            String result = JSON.toJSONString(new JSONObject(map));
            return result;
        }
    }

    @RequestMapping("/delete/{commentId}")
    public String delete(@PathVariable("commentId") Integer commentId,
                         HttpSession session){
        Comment comment = commentService.findCommentById(commentId);
        Integer articleId = comment.getArticleId();
        User user = (User) session.getAttribute("user");
        if (user == null){
            return "redirect:/home/login";
        } else if (user.getUserId() != comment.getCommentAuthorId()){
            return "redirect:/home/article/" + articleId;
        }
        commentService.deleteComment(commentId);

        return "redirect:/home/article/" + articleId;
    }
}
