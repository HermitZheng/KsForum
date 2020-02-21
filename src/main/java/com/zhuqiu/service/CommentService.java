package com.zhuqiu.service;

import com.github.pagehelper.PageInfo;
import com.zhuqiu.pojo.Comment;

import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/4
 */
public interface CommentService {



    /**
     * 根据文章id获取评论列表
     *
     * @param articleId 文章ID
     * @return 列表
     */
    List<Comment> listCommentByArticleId(Integer articleId);

    /**
     * 列出用户的所有评论
     * @param userId
     * @return 评论列表
     */
    List<Comment> listCommentByUserId(Integer userId);

    /**
     * 根据id获取评论
     *
     * @param commentId
     * @return
     */
    Comment findCommentById(Integer commentId);


    /**
     * 获得评论列表
     *
     * @return 列表
     */
    List<Comment> findAllComment();


    /**
     * 删除评论
     *
     * @param commentId ID
     */
    void deleteComment(Integer commentId);

    /**
     * 修改评论
     *
     * @param comment 评论
     */
    void updateComment(Comment comment);

    /**
     * 添加评论
     *
     * @param comment 评论
     */
    Comment insertComment(Comment comment);

    /**
     * 统计评论数
     *
     * @return 数量
     */
    Integer countComment();

    /**
     * 获得最近评论
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Comment> listRecentComment(Integer limit);

    /**
     * 获得评论的子评论
     *
     * @param commentId 父评论ID
     * @return 列表
     */
    List<Comment> listChildComment(Integer commentId);

    /**
     * 获取所有评论列表
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示数量
     * @return 列表
     */
    PageInfo<Comment> listCommentByPage(Integer pageIndex,
                                        Integer pageSize);

}
