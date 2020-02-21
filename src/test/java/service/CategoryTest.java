package service;

import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/6
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
@WebAppConfiguration
public class CategoryTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void insertCategory(){
        Category category = new Category("Java");
//        categoryService.insertCategory(category);

        List<Category> categoryList = categoryService.listCategory();

        System.out.println(categoryList);
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void listCategory(){
        List<Category> categoryList = categoryService.listCategoryWithArticleCount();
        for (Category category : categoryList) {
            System.out.println(category);
        }
    }

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚
    public void insertRecord(){
        Article article = new Article();
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(2));
        article.setCategoryList(categoryList);
        article.setArticleId(1);

        articleService.updateArticleDetail(article);
        System.out.println(categoryService.listCategoryByArticleId(article.getArticleId()));
    }
}
