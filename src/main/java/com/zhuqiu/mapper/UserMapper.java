package com.zhuqiu.mapper;

import com.zhuqiu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  用户信息
 * @author zhuqiu
 * @date 2020/2/1
 */

@Repository
@Mapper
public interface UserMapper {

    /**
     * 按用户Id查询单个用户
     * @param userId
     * @return 一个用户
     */
    User findUserById(Integer userId);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> findAllUser();

    /**
     * 更新用户信息
     * @param user
     * @return 影响行数
     */
    int updateUser(User user);

    /**
     * 按Id删除用户
     * @param userId
     * @return 影响行数
     */
    int deleteUserById(Integer userId);

    /**
     * 新增一个用户
     * @param user
     * @return 影响行数
     */
    int insertUser(User user);

    /**
     * 按Name查询用户（模糊查找）
     * @param userNickname
     * @return 用户列表用户
     */
    List<User> findUserByNickName(String userNickname);

    /**
     * 按用户名查询用户（用于登录）
     * @param userName
     * @return User
     */
    User findUserByName(String userName);

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
     * 根据id计算用户评论数量
     * @param userId
     * @return 评论数量
     */
    Integer countUserComment(Integer userId);

    /**
     * 根据id计算用户收藏数量
     * @param userId
     * @return 收藏数量
     */
    Integer countUserFavorite(Integer userId);


    /**
     * 根据id查找收藏文章的用户
     * @param articleId
     * @return 用户列表
     */
    List<User> listFavoriteUser(Integer articleId);


}
