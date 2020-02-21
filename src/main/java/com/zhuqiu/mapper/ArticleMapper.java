package com.zhuqiu.mapper;

import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * 文章信息
 *
 * @author zhuqiu
 * @date 2020/2/1
 */

@Repository
@Mapper
public interface ArticleMapper {

    //基本功能

    /**
     * 根据Id查找文章
     * @param articleId
     * @return 一篇文章
     */
    Article findArticleById(Integer articleId);

    /**
     * 根据id查找文章（无正文—）
     * @param articleId
     * @return 一篇文章
     */
    Article findArticleNotWithContent(Integer articleId);

    /**
     * 按标题查询文章(模糊查询)
     * @param articleTitle
     * @return
     */
    List<Article> findArticleByTitle(String articleTitle);

    /**
     * 根据分类ID查询文章
     *
     * @param categoryId 分类ID
     * @param limit      查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryId(@Param("categoryId") Integer categoryId,
                                          @Param("limit") Integer limit);

    /**
     * 根据多个分类ID查询文章
     *
     * @param categoryIds 分类ID集合
     * @param limit       查询数量
     * @return 文章列表
     */
    List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                           @Param("limit") Integer limit);

    /**
     * 根据id查找用户收藏文章
     * @param userId
     * @return 文章列表
     */
    List<Article> listFavoriteArticle(Integer userId);

    /**
     * 根据id查找用户发布文章列表
     * @param userId
     * @return 文章列表
     */
    List<Article> listUserArticle(Integer userId);

    /**
     * 查询所有文章
     * @param criteria 搜索条件列表
     * @return 文章列表
     */
    List<Article> findAllArticle(HashMap<String, Object> criteria);

    /**
     * 查询所有文章（不带正文）
     * @param criteria 搜索条件列表
     * @return 文章列表
     */
    List<Article> findAllArticleNotWithContent(HashMap<String, Object> criteria);

    /**
     * 新增一篇文章
     * @param article
     * @return 自增Id的值
     */
    int insertArticle(Article article);

    /**
     * 更新文章信息
     * @param article
     * @return 影响行数
     */
    int updateArticle(Article article);

    /**
     * 按Id删除文章
     * @param articleId
     * @return 影响行数
     */
    int deleteArticleById(Integer articleId);

    /**
     * 批量删除文章
     *
     * @param ids 文章Id列表
     * @return 影响行数
     */
    int deleteBatch(@Param("ids") List<Integer> ids);


    //计算论坛总数

    /**
     * 计算文章总数
     * @return 文章总数
     */
    Integer countArticle();

    /**
     * 获得总文章浏览数
     * @return 总浏览数
     */
    Integer countAllArticleView();

    /**
     * 计算总文章评论数
     * @return 总评论数
     */
    Integer countAllArticleComment();


    //单篇信息

    /**
     * 获得文章浏览数
     * @param articleId
     * @return 浏览数
     */
    Integer countArticleViewById(Integer articleId);

    /**
     * 更新文章评论数
     * @param articleId
     * @return 影响行数
     */
    int updateCommentCount(Integer articleId);


    /**
     * 获得文章赞数
     * @param articleId
     * @return 赞数
     */
    Integer countArticleLikeById(Integer articleId);

    /**
     * 更新文章收藏数
     * @param articleId
     * @return 影响行数
     */
    int updateFavoriteCount(Integer articleId);


}
