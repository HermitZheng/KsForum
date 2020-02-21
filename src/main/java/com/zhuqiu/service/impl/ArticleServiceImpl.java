package com.zhuqiu.service.impl;

import com.zhuqiu.mapper.ArticleCategoryRefMapper;
import com.zhuqiu.mapper.ArticleMapper;
import com.zhuqiu.mapper.UserMapper;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.ArticleCategoryRef;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/4
 */

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired(required = false)
    private ArticleMapper articleMapper;

    @Autowired(required = false)
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired(required = false)
    private UserMapper userMapper;



    @Override
    public Integer countArticle() {
        Integer count = null;
        try {
            count = articleMapper.countArticle();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取文章总数失败，cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countAllArticleComment() {
        Integer count = null;
        try {
            count = articleMapper.countAllArticleComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取文章评论数失败，cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countAllArticleView() {
        Integer count = null;
        try {
            count = articleMapper.countAllArticleView();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取文章浏览数失败，cause:{}", e);
        }
        return count;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        Integer count = null;
        try {
            count = articleCategoryRefMapper.countArticleByCategoryId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据类别统计文章数失败，categoryId:{}, cause:{}", categoryId, e);
        }
        return count;
    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.findAllArticle(criteria);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取文章列表失败，criteria:{}, cause:{}", criteria, e);
        }
        return articleList;
    }

    @Override
    public List<Article> listAllNotWithContent(HashMap<String, Object> criteria) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.findAllArticleNotWithContent(criteria);
            for (Article article : articleList) {
                User user = userMapper.findUserById(article.getArticleUserId());
                article.setUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取文章（无正文）列表失败，criteria:{}, cause:{}", criteria, e);
        }
        return articleList;
    }

    @Override
    public List<Article> listAllWithUser(HashMap<String, Object> criteria) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.findAllArticleNotWithContent(criteria);
            for (Article article : articleList) {
                User user = userMapper.findUserById(article.getArticleUserId());
                article.setUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取文章（无正文）列表失败，criteria:{}, cause:{}", criteria, e);
        }
        return articleList;
    }

    @Override
    public void updateArticleDetail(Article article) {
        try {
            article.setArticleUpdateTime(new Date());
            articleMapper.updateArticle(article);
            // 重新关联分类信息
            if (article.getCategoryList() != null){
                articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
                for (Category category : article.getCategoryList()) {
                    articleCategoryRefMapper.insertRecord(
                            new ArticleCategoryRef(article.getArticleId(), category.getCategoryId()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新文章信息失败，article:{}, cause:{}", article, e);
        }
    }

    @Override
    public void updateArticle(Article article) {
        try {
            article.setArticleUpdateTime(new Date());
            articleMapper.updateArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新文章信息失败，article:{}, cause:{}", article, e);
        }
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {
        try {
            articleMapper.deleteBatch(ids);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("批量删除文章失败，ids:{}, cause:{}", ids, e);
        }
    }

    @Override
    public void deleteArticle(Integer articleId) {
        try {
            articleMapper.deleteArticleById(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除文章失败，articleId:{}, cause:{}", articleId, e);
        }
    }

    @Override
    public Article findArticleById(Integer articleId) {
        Article article = null;
        try {
            article = articleMapper.findArticleById(articleId);
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(articleId);
            article.setCategoryList(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查找文章详细信息失败，articleId:{}, cause:{}", articleId, e);
        }
        return article;
    }

    @Override
    public Article findArticleNotWithContentById(Integer articleId) {
        Article article = null;
        try {
            article = articleMapper.findArticleNotWithContent(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id查找文章基本信息失败，articleId:{}, cause:{}", articleId, e);
        }
        return article;
    }

    @Override
    public List<Article> findArticleByTitle(String articleTitle) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.findArticleByTitle(articleTitle);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据标题查找文章基本信息失败，articleTitle:{}, cause:{}", articleTitle, e);
        }
        return articleList;
    }

//    @Override
//    public List<Article> findArticleNotWithContentByTitle(String articleId) {
//        return null;
//    }

    @Override
    public void insertArticle(Article article) {
        try {
            //获取自增长Id的值
            articleMapper.insertArticle(article);
            //关联分类信息
            if (article.getCategoryList() != null){
                articleCategoryRefMapper.deleteByArticleId(article.getArticleId());
                for (Category category : article.getCategoryList()) {
                    articleCategoryRefMapper.insertRecord(
                            new ArticleCategoryRef(article.getArticleId(), category.getCategoryId()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增文章失败，article:{}, cause:{}", article, e);
        }
    }

    @Override
    public void updateCommentCount(Integer articleId) {
        try {
            articleMapper.updateCommentCount(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章id更新评论数失败，articleId:{}, cause:{}", articleId, e);
        }
    }

//    @Override
//    public void updateViewCount(Integer articleId) {
//
//    }

    @Override
    public void updateFavoriteCount(Integer articleId) {
        try {
            articleMapper.updateFavoriteCount(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章id更新收藏数失败，articleId:{}, cause:{}", articleId, e);
        }
    }

//    @Override
//    public void updateLikeCount(Integer articleId) {
//
//    }

    @Override
    public List<Article> listArticleByCategoryId(Integer categoryId, Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.findArticleByCategoryId(categoryId, limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据分类id查找文章失败，categoryId:{}, limit:{}, cause:{}", categoryId, limit, e);
        }
        return articleList;
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> categoryIds, Integer limit) {
        List<Article> articleList = null;
        try {
            articleList = articleMapper.findArticleByCategoryIds(categoryIds, limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据分类id查找文章失败，categoryIds:{}, limit:{}, cause:{}", categoryIds, limit, e);

        }
        return articleList;
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        List<Integer> list = null;
        try {
            list = articleCategoryRefMapper.listCategoryIdByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章id查找分类列表失败，articleId:{}, cause:{}", articleId, e);
        }
        return list;
    }
}
