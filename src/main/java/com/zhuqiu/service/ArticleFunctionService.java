package com.zhuqiu.service;

import com.github.pagehelper.PageInfo;
import com.zhuqiu.pojo.Article;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/4
 */
public interface ArticleFunctionService {

    /**
     * 分页显示
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 文章列表
     */
    PageInfo<Article> pageArticle(Integer pageIndex,
                                  Integer pageSize,
                                  HashMap<String, Object> criteria);

    /**
     * 获得访问最多的文章(猜你喜欢)
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * 列出收藏数量前几的文章
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByFavoriteCount(Integer limit);

    /**
     * 列出点赞数量前几的文章
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByLikeCount(Integer limit);

    /**
     * 热评文章
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByCommentCount(Integer limit);


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
     * @return 列表
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * 获得最后更新的记录
     *
     * @return 文章
     */
    List<Article> getLastUpdateArticle(Integer limit);

    /**
     * 获得最近发布的文章
     *
     * @return 文章
     */
    List<Article> getLastCreateArticle(Integer limit);


}
