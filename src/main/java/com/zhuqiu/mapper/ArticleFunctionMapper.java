package com.zhuqiu.mapper;

import com.zhuqiu.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章的推荐、分页、评论、收藏、赞、浏览量更新
 *
 * @author zhuqiu
 * @date 2020/2/1
 */

@Repository
@Mapper
public interface ArticleFunctionMapper {

    /**
     * 获得访问最多的文章(猜你喜欢)
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);

    /**
     * 列出收藏数量前几的文章
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByFavoriteCount(@Param(value = "limit") Integer limit);

    /**
     * 列出点赞数量前几的文章
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByLikeCount(@Param(value = "limit") Integer limit);

    /**
     * 热评文章
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);

    /**
     * 最新发布文章
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByCreateTime(@Param(value = "limit") Integer limit);

    /**
     * 分页操作
     *
     * @param pageIndex 从第几页开始
     * @param pageSize  数量
     * @return 文章列表
     */
    @Deprecated
    List<Article> pageArticle(@Param(value = "pageIndex") Integer pageIndex,
                              @Param(value = "pageSize") Integer pageSize);

    /**
     * 获得上一篇文章
     *
     * @param articleId 文章ID
     * @return 文章
     */
    Article getAfterArticle(Integer articleId);

    /**
     * 获得下一篇文章
     *
     * @param articleId 文章ID
     * @return 文章
     */
    Article getPreArticle(Integer articleId);

    /**
     * 获得随机文章
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listRandomArticle(@Param(value = "limit") Integer limit);


    /**
     * 获得最近更新的记录
     *
     * @return 文章
     */
    List<Article> getLastUpdateArticle(@Param(value = "limit") Integer limit);

    /**
     * 获得最近更新的记录
     *
     * @return 文章
     */
    List<Article> getLastCreateArticle(@Param(value = "limit") Integer limit);


    //同步更新文章信息

    /**
     * 更新文章的评论数
     * @param articleId
     * @return 影响行数
     */
    int updateCommentCount(Integer articleId);

    /**
     * 更新文章的收藏数
     * @param articleId
     * @return 影响行数
     */
    int updateFavoriteCount(Integer articleId);

    /**
     * 更新文章的浏览量
     * @param articleId
     * @return 影响行数
     */
    int updateViewCount(Integer articleId);

    /**
     * 更新文章的赞数
     * @param articleId
     * @return 影响行数
     */
    int updateLikeCount(Integer articleId);

}
