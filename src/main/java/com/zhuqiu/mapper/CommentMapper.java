package com.zhuqiu.mapper;

import com.zhuqiu.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论信息
 * @author zhuqiu
 * @date 2020/2/2
 */

@Repository
@Mapper
public interface CommentMapper {

    /**
     * 根据id查询评论
     * @param commentId
     * @return 评论
     */
    Comment findCommentById(Integer commentId);

    /**
     * 列出所以评论
     * @return 评论列表
     */
    List<Comment> findAllComment();

    /**
     * 列出文章的所有评论
     * @param articleId
     * @return 评论列表
     */
    List<Comment> listCommentByArticle(Integer articleId);

    /**
     * 列出用户的所有评论
     * @param userId
     * @return 评论列表
     */
    List<Comment> listCommentByUser(Integer userId);

    /**
     * 根据ID删除
     *
     * @param commentId 评论ID
     * @return 影响行数
     */
    int deleteCommentById(Integer commentId);

    /**
     * 添加
     *
     * @param comment 评论
     * @return 影响行数
     */
    int insertComment(Comment comment);

    /**
     * 更新
     *
     * @param comment 评论
     * @return 影响行数
     */
    int updateComment(Comment comment);

    /**
     * 计算总评论数
     * @return
     */
    Integer countComment();



    /**
     * 获得评论的子评论
     *
     * @param commentId 父评论ID
     * @return 列表
     */
    List<Comment> listChildComment(Integer commentId);

    /**
     * 获得最近评论
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Comment> listRecentComment(@Param(value = "limit") Integer limit);

}
