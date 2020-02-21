package com.zhuqiu.service;

import com.zhuqiu.pojo.Article;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/4
 */
public interface ArticleService {

    /**
     * 获取文章总数
     *
     * @return 数量
     */
    Integer countArticle();

    /**
     * 获取评论总数
     *
     * @return 数量
     */
    Integer countAllArticleComment();

    /**
     * 获得浏览量总数
     *
     * @return 数量
     */
    Integer countAllArticleView();


    /**
     * 统计有这个分类的文章数
     *
     * @param categoryId 分类ID
     * @return 数量
     */
    Integer countArticleByCategoryId(Integer categoryId);


    /**
     * 获得所有文章不分页
     *
     * @param criteria 查询条件
     * @return 列表
     */
    List<Article> listArticle(HashMap<String, Object> criteria);

    /**
     * 获得所有的文章
     *
     * @return 列表
     */
    List<Article> listAllNotWithContent(HashMap<String, Object> criteria);

    /**
     * 获取所有文章，并附带用户信息
     * @param criteria
     * @return
     */
    List<Article> listAllWithUser(HashMap<String, Object> criteria);




    /**
     * 修改文章详细信息
     *
     * @param article 文章
     */
    void updateArticleDetail(Article article);

    /**
     * 修改文章简单信息
     *
     * @param article 文章
     */
    void updateArticle(Article article);

    /**
     * 批量删除文章
     *
     * @param ids 文章ID
     */
    void deleteArticleBatch(List<Integer> ids);

    /**
     * 删除文章
     *
     * @param articleId 文章ID
     */
    void deleteArticle(Integer articleId);


    /**
     * 文章基本
     *
     * @param articleId     文章ID
     * @return 文章
     */
    Article findArticleById(Integer articleId);

    /**
     * 文章详细信息
     *
     * @param articleId     文章ID
     * @return 文章
     */
    Article findArticleNotWithContentById(Integer articleId);

    /**
     * 文章详情页面显示
     *
     * @param articleTitle     文章标题
     * @return 文章
     */
    List<Article> findArticleByTitle(String articleTitle);

    /**
     * 文章基本信息
     *
     * @param articleTitle     文章标题
     * @return 文章
     */
//    List<Article> findArticleNotWithContentByTitle(String articleTitle);



    /**
     * 添加文章
     *
     * @param article 文章
     */
    void insertArticle(Article article);


    /**
     * 更新文章的评论数
     *
     * @param articleId 文章ID
     */
    void updateCommentCount(Integer articleId);

    /**
     * 更新文章的浏览数
     *
     * @param articleId 文章ID
     */
//    void updateViewCount(Integer articleId);

    /**
     * 更新文章的收藏数
     *
     * @param articleId 文章ID
     */
    void updateFavoriteCount(Integer articleId);

    /**
     * 更新文章的点赞数
     *
     * @param articleId 文章ID
     */
//    void updateLikeCount(Integer articleId);

    /**
     * 获得类别相关文章
     *
     * @param categoryId 分类ID
     * @param limit  查询数量
     * @return 列表
     */
    List<Article> listArticleByCategoryId(Integer categoryId, Integer limit);

    /**
     * 获得相关文章
     *
     * @param categoryIds 分类ID集合
     * @param limit   数量
     * @return 列表
     */
    List<Article> listArticleByCategoryIds(List<Integer> categoryIds, Integer limit);


    /**
     * 根据文章ID获得分类ID列表
     *
     * @param articleId 文章Id
     * @return 列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);


}
