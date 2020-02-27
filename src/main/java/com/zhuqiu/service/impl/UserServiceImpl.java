package com.zhuqiu.service.impl;

import com.zhuqiu.mapper.ArticleMapper;
import com.zhuqiu.mapper.CommentMapper;
import com.zhuqiu.mapper.UserFavoriteRefMapper;
import com.zhuqiu.mapper.UserMapper;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.pojo.User;
import com.zhuqiu.pojo.UserFavoriteRef;
import com.zhuqiu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/2
 */

@Service
@Slf4j
@MapperScan("com.zhuqiu.mapper")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private UserFavoriteRefMapper userFavoriteRefMapper;

    @Override
    public User findAdminByName(String userName) {
        User user = null;
        try {
            user = userMapper.findAdminByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据用户名查找用户失败：userName:{}, cause:{}", userName, e);
        }
        return user;
    }

    @Override
    public User findUserById(Integer userId) {
        User user = null;
        try {
            user = userMapper.findUserById(userId);
            user.setUserArticleCount(userMapper.countUserArticle(userId));
            user.setUserFavoriteCount(userMapper.countUserArticle(userId));
            user.setUserCommentCount(userMapper.countUserComment(userId));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查找用户失败：userId:{}, cause:{}", userId, e);
        }
        return user;
    }

    @Override
    public User findUserByName(String userName) {
        User user = null;
        try {
            user = userMapper.findUserByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据用户名查找用户失败：userName:{}, cause:{}", userName, e);
        }
        return user;
    }

    @Override
    public List<User> findAllUser() {
        List<User> userList = null;
        try {
            userList = userMapper.findAllUser();
            for (User user : userList) {
                user.setUserArticleCount(userMapper.countUserArticle(user.getUserId()));
//                user.setUserFavoriteCount(userMapper.countUserArticle(user.getUserId()));
                user.setUserCommentCount(userMapper.countUserComment(user.getUserId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户失败：ause:{}", e);
        }
        return userList;
    }

    @Override
    public void updateUser(User user) {
        try {
            userMapper.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新用户失败：user:{}, cause:{}", user, e);
        }
    }

    @Override
    public void deleteUserById(Integer userId) {
        try {
            userMapper.deleteUserById(userId);
            userFavoriteRefMapper.deleteByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除用户失败：userId:{}, cause:{}", userId, e);
        }
    }

    @Override
    public User insertUser(User user) {
        try {
            user.setUserRegisterTime(new Date());
            userMapper.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建用户失败：user:{}, cause:{}", user, e);
        }
        return user;
    }

    @Override
    public List<User> findUserByNickName(String userNickname) {
        List<User> userList = null;
        try {
            userList = userMapper.findUserByNickName(userNickname);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户失败：userNickname:{}, cause:{}", userNickname, e);
        }
        return userList;
    }

    @Override
    public Integer countUser() {
        Integer countUser = null;
        try {
            countUser = userMapper.countUser();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户数量失败：cause:{}", e);
        }
        return countUser;
    }

    @Override
    public Integer countUserArticle(Integer userId) {
        Integer countUserArticle = null;
        try {
            countUserArticle = userMapper.countUserArticle(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户文章数量失败：userId:{}, cause:{}", userId, e);
        }
        return countUserArticle;
    }

    @Override
    public List<Article> listUserArticle(Integer userId) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.listUserArticle(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户文章列表失败：userId:{}, cause:{}", userId, e);
        }
        return articleList;
    }

    @Override
    public Integer countUserComment(Integer userId) {
        Integer countUserComment = null;
        try {
            countUserComment = userMapper.countUserComment(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户评论数量失败：userId:{}, cause:{}", userId, e);
        }
        return countUserComment;
    }

    @Override
    public List<Comment> listUserComment(Integer userId) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listCommentByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户评论列表失败：userId:{}, cause:{}", userId, e);
        }
        return commentList;
    }

    @Override
    public Integer countUserFavorite(Integer userId) {
        Integer countUserFavorite = null;
        try {
            countUserFavorite = userMapper.countUserFavorite(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户收藏数量失败：userId:{}, cause:{}", userId, e);
        }
        return countUserFavorite;
    }

    @Override
    public List<Article> listFavoriteArticle(Integer userId) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.listFavoriteArticle(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户收藏列表失败：userId:{}, cause:{}", userId, e);
        }
        return articleList;
    }

    @Override
    public Integer addFavorite(Integer userId, Integer articleId) {
        try {
            List<Integer> articleIdList = userFavoriteRefMapper.findFavoriteByUserId(userId);
            if (!articleIdList.contains(articleId)){
                userFavoriteRefMapper.insertRecord(new UserFavoriteRef(userId, articleId));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户收藏失败：userId:{}, articleId:{}, cause:{}", userId, articleId, e);
        }
        return articleId;
    }

    @Override
    public Integer deleteFavorite(Integer userId, Integer articleId) {
        try {
            userFavoriteRefMapper.deleteRecord(new UserFavoriteRef(userId, articleId));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户取消收藏失败：userId:{}, articleId:{}, cause:{}", userId, articleId, e);
        }
        return articleId;
    }

    @Override
    public List<Integer> listFavoriteArticleId(Integer userId) {
        List<Integer> list = null;
        try {
            list = userFavoriteRefMapper.findFavoriteByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取用户收藏id列表失败：userId:{}, cause:{}", userId, e);
        }
        return list;
    }
}
