package com.zhuqiu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuqiu.mapper.ArticleMapper;
import com.zhuqiu.mapper.CommentMapper;
import com.zhuqiu.mapper.UserMapper;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/4
 */

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listCommentByArticle(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得评论列表失败：cause:{}", e);
        }
        return commentList;
    }

    @Override
    public List<Comment> listCommentByUserId(Integer userId) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listCommentByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据用户ID获得评论列表失败，userId:{}, cause:{}", userId, e);
        }
        return commentList;
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        Comment comment = null;
        try {
            comment = commentMapper.findCommentById(commentId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据ID获得评论失败，commentId:{}, cause:{}", commentId, e);
        }
        return comment;
    }

    @Override
    public List<Comment> findAllComment() {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.findAllComment();
//            for (Comment comment : commentList) {
//                User user = userMapper.findUserById(comment.getCommentAuthorId());
//                comment.setCommentAuthorAvatar(user.getUserAvatar());
//            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得评论列表失败，cause:{}", e);
        }
        return commentList;
    }

    @Override
    public void deleteComment(Integer commentId) {
        try {
            commentMapper.deleteCommentById(commentId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除评论失败, commentId:{}, cause:{}", commentId, e);
        }
    }

    @Override
    public void updateComment(Comment comment) {
        try {
            commentMapper.updateComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新评论失败, comment:{}, cause:{}", comment, e);
        }
    }

    @Override
    public Comment insertComment(Comment comment) {
        try {
            commentMapper.insertComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增评论失败, comment:{}, cause:{}", comment, e);
        }
        return comment;
    }

    @Override
    public Integer countComment() {
        Integer countComment = null;
        try {
            countComment = commentMapper.countComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计评论数量失败, cause:{}", e);        }
        return countComment;
    }

    @Override
    public List<Comment> listRecentComment(Integer limit) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listRecentComment(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得最新评论失败, limit:{}, cause:{}", limit, e);
        }
        return commentList;
    }

    @Override
    public List<Comment> listChildComment(Integer commentId) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listChildComment(commentId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得子评论失败, commentId:{}, cause:{}", commentId, e);
        }
        return commentList;
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.findAllComment();
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleMapper.findArticleById(commentList.get(i).getArticleId());
                commentList.get(i).setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("分页获得评论失败,pageIndex:{}, pageSize:{}, cause:{}", pageIndex, pageSize, e);
        }
        return new PageInfo<>(commentList);
    }
}
