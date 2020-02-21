package com.zhuqiu.service;

import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.pojo.User;

import java.util.List;

/**
 *
 * @author zhuqiu
 * @date 2020/2/2
 */

public interface UserService {

    /**
     * 按用户Id查询单个用户
     * @param userId
     * @return 一个用户
     */
    User findUserById(Integer userId);

    /**
     * 按用户名查询用户（用于登录）
     * @param userName
     * @return User
     */
    User findUserByName(String userName);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> findAllUser();

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 按Id删除用户
     * @param userId
     */
    void deleteUserById(Integer userId);

    /**
     * 新增一个用户
     * @param user
     */
    User insertUser(User user);

    /**
     * 按Name查询用户（模糊查找）
     * @param userNickname
     * @return 用户列表用户
     */
    List<User> findUserByNickName(String userNickname);

    /**
     * 计算用户总数
     * @return 用户总数
     */
    Integer countUser();


    //用户发文、评论、收藏信息

    /**
     * 根据id计算用户发布文章数量
     * @param userId
     * @return 文章数量
     */
    Integer countUserArticle(Integer userId);

    /**
     * 根据id查找用户发布文章列表
     * @param userId
     * @return 文章列表
     */
    List<Article> listUserArticle(Integer userId);


    /**
     * 根据id计算用户评论数量
     * @param userId
     * @return 评论数量
     */
    Integer countUserComment(Integer userId);

    /**
     * 根据id查找用户评论
     * @param userId
     * @return 评论列表
     */
    List<Comment> listUserComment(Integer userId);

    /**
     * 根据id计算用户收藏数量
     * @param userId
     * @return 收藏数量
     */
    Integer countUserFavorite(Integer userId);

    /**
     * 根据id查找用户收藏文章
     * @param userId
     * @return 文章列表
     */
    List<Article> listFavoriteArticle(Integer userId);

    /**
     * 添加收藏文章
     * @param userId
     * @param articleId
     * @return articleId
     */
    Integer addFavorite(Integer userId, Integer articleId);

    /**
     * 删除收藏文章
     * @param userId
     * @param articleId
     * @return articleId
     */
    Integer deleteFavorite(Integer userId, Integer articleId);

    /**
     * 根据userId查找收藏文章id列表
     * @param userId
     * @return 收藏文章id列表
     */
    List<Integer> listFavoriteArticleId(Integer userId);
}
