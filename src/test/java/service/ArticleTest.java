package service;

import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.ArticleCategoryRef;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/6
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class ArticleTest {

    @Autowired
    private ArticleService articleService;



    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void insertArticle(){
        Article article = new Article();
        article.setArticleUserId(1);
        article.setArticleTitle("第一次测试Title");
        article.setArticleContent("第一次测试内容，无格式，Content。");
        Date date = new Date();
        article.setArticleCreateTime(date);
        article.setArticleUpdateTime(date);

        articleService.insertArticle(article);

        System.out.println(articleService.findArticleById(1));
        HashMap<String, Object> map = new HashMap<>();
        map.put("keywords", "测试");
        List<Article> articleList = articleService.listArticle(map);

        System.out.println(articleList);
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void setCategory(){
        Article article = articleService.findArticleById(1);
        Category category = new Category(1, "Java");
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        article.setCategoryList(categoryList);

        articleService.updateArticleDetail(article);

        System.out.println(articleService.findArticleByTitle("测试"));
    }
}
