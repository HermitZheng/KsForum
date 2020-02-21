package com.zhuqiu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuqiu.mapper.ArticleCategoryRefMapper;
import com.zhuqiu.mapper.ArticleFunctionMapper;
import com.zhuqiu.mapper.ArticleMapper;
import com.zhuqiu.mapper.UserMapper;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.ArticleFunctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/5
 */

@Slf4j
@Service
public class ArticleFunctionServiceImpl implements ArticleFunctionService {

    @Autowired(required = false)
    private ArticleFunctionMapper articleFunctionMapper;

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private ArticleCategoryRefMapper categoryRefMapper;

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        List<Article>articleList = null;
        try {
            PageHelper.startPage(pageIndex, pageSize);
            articleList = articleMapper.findAllArticle(criteria);
            for (Article article : articleList) {
                List<Category> categoryList = categoryRefMapper.listCategoryByArticleId(article.getArticleId());
                article.setCategoryList(categoryList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("分页获取文章失败，pageIndex:{}, pageSize:{}, criteria:{}", pageIndex, pageSize, criteria);
        }
        return new PageInfo<>(articleList);
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleFunctionMapper.listArticleByViewCount(limit);
            for (Article article : articleList) {
                User user = userMapper.findUserById(article.getArticleUserId());
                article.setUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据浏览量获取文章列表失败，limit:{}, cause:{}", limit, e);
        }
        return articleList;
    }

    @Override
    public List<Article> listArticleByFavoriteCount(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleFunctionMapper.listArticleByFavoriteCount(limit);
            for (Article article : articleList) {
                User user = userMapper.findUserById(article.getArticleUserId());
                article.setUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据收藏数量获取文章列表失败，limit:{}, cause:{}", limit, e);
        }
        return articleList;
    }

    @Override
    public List<Article> listArticleByLikeCount(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleFunctionMapper.listArticleByLikeCount(limit);
            for (Article article : articleList) {
                User user = userMapper.findUserById(article.getArticleUserId());
                article.setUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据点赞数获取文章列表失败，limit:{}, cause:{}", limit, e);
        }
        return articleList;
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleFunctionMapper.listArticleByCommentCount(limit);
            for (Article article : articleList) {
                User user = userMapper.findUserById(article.getArticleUserId());
                List<Category> categoryList = categoryRefMapper.listCategoryByArticleId(article.getArticleId());
                article.setUser(user);
                article.setCategoryList(categoryList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据评论数量获取文章列表失败，limit:{}, cause:{}", limit, e);
        }
        return articleList;
    }

    @Override
    public Article getAfterArticle(Integer articleId) {
        Article article = null;
        try {
            article = articleFunctionMapper.getAfterArticle(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取下一篇文章失败，articleId:{}, cause:{}", articleId, e);
        }
        return article;
    }

    @Override
    public Article getPreArticle(Integer articleId) {
        Article article = null;
        try {
            article = articleFunctionMapper.getPreArticle(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取上一篇文章失败，articleId:{}, cause:{}", articleId, e);
        }
        return article;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleFunctionMapper.listRandomArticle(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取随机文章列表失败，limit:{}, cause:{}", limit, e);
        }
        return articleList;
    }

    @Override
    public List<Article> getLastUpdateArticle(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleFunctionMapper.getLastUpdateArticle(limit);
            for (Article article : articleList) {
                User user = userMapper.findUserById(article.getArticleUserId());
                List<Category> categoryList = categoryRefMapper.listCategoryByArticleId(article.getArticleId());
                article.setUser(user);
                article.setCategoryList(categoryList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取最新更新文章列表失败，limit:{}, cause:{}", limit, e);
        }
        return articleList;
    }

    @Override
    public List<Article> getLastCreateArticle(Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleFunctionMapper.getLastCreateArticle(limit);
            for (Article article : articleList) {
                User user = userMapper.findUserById(article.getArticleUserId());
                article.setUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取最近发布文章列表失败，limit:{}, cause:{}", limit, e);
        }
        return articleList;
    }
}
