package com.zhuqiu.controller.backend;

import com.github.pagehelper.PageInfo;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/10
 */

@Controller("backCommentController")
@RequestMapping("/back/comment")
public class BackCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;


    /**
     * 评论页面
     *
     * @param pageIndex 页码
     * @param pageSize  页大小
     * @return modelAndView
     */
    @RequestMapping(value = "")
    public String commentListView(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                  Model model) {
        PageInfo<Comment> commentPageInfo = commentService.listCommentByPage(pageIndex, pageSize);
        model.addAttribute("pageInfo", commentPageInfo);
        model.addAttribute("pageUrlPrefix","/back/comment?pageIndex");
        return "/Back/Comment/index";
    }

    /**
     * 新增评论
     * @param comment
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public void insert(Comment comment){
        comment.setCommentCreateTime(new Date());
        commentService.insertComment(comment);

//        Article article = articleService.findArticleById(comment.getArticleId());
        articleService.updateCommentCount(comment.getArticleId());
    }

    /**
     * 删除评论
     * @param commentId
     */
    @RequestMapping("/delete/{commentId}")
    @ResponseBody
    public void delete(@PathVariable("commentId") Integer commentId){
        Comment comment = commentService.findCommentById(commentId);
        commentService.deleteComment(commentId);
        List<Comment> commentList = commentService.listChildComment(commentId);
        for (Comment comment1 : commentList) {
            commentService.deleteComment(comment1.getCommentId());
        }

//        Article article = articleService.findArticleById(comment.getArticleId());
        articleService.updateCommentCount(comment.getArticleId());
    }

    /**
     * 跳转至编辑评论页面
     * @param commentId
     * @param model
     * @return
     */
    @RequestMapping("/edit/{commentId}")
    public String edit(@PathVariable("commentId") Integer commentId, Model model){
        Comment comment = commentService.findCommentById(commentId);
        model.addAttribute("comment", comment);

        return "/Back/Comment/edit";
    }

    /**
     * 提交编辑评论
     * @param comment
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(Comment comment){
        commentService.updateComment(comment);
        return "redirect:/back/comment";
    }

    /**
     * 回复评论
     * @param commentId
     * @param model
     * @return
     */
    @RequestMapping("/reply/{commentId}")
    public String reply(@PathVariable("commentId") Integer commentId, Model model){
        Comment comment = commentService.findCommentById(commentId);
        model.addAttribute("comment", comment);

        return "Back/Comment/reply";
    }

    /**
     * 提交评论回复
     * @param comment
     * @return
     */
    @RequestMapping(value = "/replySubmit", method = RequestMethod.POST)
    public String replySubmit(Comment comment){
//        if (true){
//            System.out.println(comment);
//            return "redirect:/back/comment";
//        }
        comment.setCommentCreateTime(new Date());

        commentService.insertComment(comment);
        articleService.updateCommentCount(comment.getArticleId());

        return "redirect:/back/comment";
    }


}
