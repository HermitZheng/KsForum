package com.zhuqiu.mapper;

import com.zhuqiu.pojo.UserFavoriteRef;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户收藏文章相关关联表
 * @author zhuqiu
 * @date 2020/2/2
 */

@Repository
@Mapper
public interface UserFavoriteRefMapper {

    /**
     * 根据用户id查找记录
     * @param userId
     * @return 文章id列表
     */
    List<Integer> findFavoriteByUserId(Integer userId);

    /**
     * 根据文章id查找记录
     * @param articleId
     * @return 用户id列表
     */
    List<Integer> findFavoriteByArticleId(Integer articleId);

    /**
     * 新增一条收藏记录
     * @param record
     * @return 影响行数
     */
    int insertRecord(UserFavoriteRef record);

    /**
     * 根据id计算用户收藏数量
     * @param userId
     * @return 收藏数量
     */
    Integer countUserFavorite(Integer userId);



    /**
     * 根据id计算文章被收藏数量
     * @param articleId
     * @return 收藏数量
     */
    Integer countArticleFavorite(Integer articleId);



    /**
     * 按用户id删除记录
     * @param userId
     * @return 影响行数
     */
    int deleteByUserId(Integer userId);

    /**
     * 按文章id删除记录
     * @param articleId
     * @return 影响行数
     */
    int deleteByArticleId(Integer articleId);

    /**
     * 用户取消收藏
     * @param record
     * @return 影响行数
     */
    int deleteRecord(UserFavoriteRef record);
}
