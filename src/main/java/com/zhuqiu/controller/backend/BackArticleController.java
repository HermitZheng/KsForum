package com.zhuqiu.controller.backend;

import com.github.pagehelper.PageInfo;
import com.zhuqiu.dto.ArticleParam;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.ArticleFunctionService;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/10
 */

@Controller("backArticleController")
@RequestMapping("/back/article")
public class BackArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleFunctionService functionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 列出所有文章并进行分页展示
     *
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        Model model) {
        HashMap<String, Object> criteria = new HashMap<>();
        model.addAttribute("pageUrlPrefix", "/back/article/page");
        PageInfo<Article> articlePageInfo = functionService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "/Back/Article/index";
    }

    /**
     * 列出所有公告并进行分页展示
     *
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/notice")
    public String notice(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                        Model model) {
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(3);
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("articleStatus", statusList);
        model.addAttribute("pageUrlPrefix", "/back/article/page");
        PageInfo<Article> articlePageInfo = functionService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "/Back/Article/index";
    }

    @RequestMapping("/top")
    public String top(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                         Model model) {
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(2);
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("articleStatus", statusList);
        model.addAttribute("pageUrlPrefix", "/back/article/page");
        PageInfo<Article> articlePageInfo = functionService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "/Back/Article/index";
    }

    @RequestMapping("/draft")
    public String draft(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                         Model model) {
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(0);
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("articleStatus", statusList);
        model.addAttribute("pageUrlPrefix", "/back/article/page");
        PageInfo<Article> articlePageInfo = functionService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);
        return "/Back/Article/index";
    }

    /**
     * 跳转至新增文章页面
     *
     * @return
     */
    @RequestMapping("/insert")
    public String insert(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);
//        model.addAttribute("hold", "文章正文");
        return "/Back/Article/insert";
    }

    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertSubmit(HttpSession session, ArticleParam articleParam) {
        Article article = new Article();
        User user = (User) session.getAttribute("admin");
        if (user != null){
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        Integer status = articleParam.getArticleStatus();
        article.setArticleStatus(status);
        Date date = new Date();
        if (status != 0) {        //为1则发布， 为0则为草稿
            article.setArticleCreateTime(date);
        }
        article.setArticleUpdateTime(date);

        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
            if (articleParam.getArticleChildCategoryId() != null) {
                categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
            }
        }

        article.setCategoryList(categoryList);
        articleService.insertArticle(article);

        redisTemplate.delete("listNoContent");
        redisTemplate.delete("articleByC");
        redisTemplate.delete("articleByUp");
        redisTemplate.delete("topList");

        return "redirect:/back/article";
    }

    @RequestMapping(value = "insertDraftSubmit", method = RequestMethod.POST)
    public String insertDraftSubmit(HttpSession session, ArticleParam articleParam){

        return "redirect:/back/article";
    }

    /**
     * 删除文章
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/delete/{articleId}")
    public String delete(@PathVariable("articleId") Integer articleId) {
        articleService.deleteArticle(articleId);

        redisTemplate.delete("listNoContent");
        redisTemplate.delete("articleByC");
        redisTemplate.delete("articleByUp");
        redisTemplate.delete("topList");

        return "redirect:/back/article";
    }


    @RequestMapping("/edit/{articleId}")
    public String edit(@PathVariable("articleId") Integer articleId, Model model){
        Article article = articleService.findArticleById(articleId);
//        System.out.println(article);
        model.addAttribute("article", article);
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);
        return "/Back/Article/edit";
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(ArticleParam articleParam){
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        Integer status = articleParam.getArticleStatus();
        article.setArticleStatus(status);
        Integer original = articleService.findArticleNotWithContentById(article.getArticleId()).getArticleStatus();
        Date date = new Date();
        if (original == 0 && status != 0) {        //为1则发布， 为0则为草稿
            article.setArticleCreateTime(date);
        }
        article.setArticleUpdateTime(date);

        List<Category> categoryList = new ArrayList<>();
        if (articleParam.getArticleParentCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if (articleParam.getArticleChildCategoryId() != null) {
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        articleService.updateArticleDetail(article);

        redisTemplate.delete("listNoContent");
        redisTemplate.delete("articleByC");
        redisTemplate.delete("articleByUp");
        redisTemplate.delete("topList");

        return "redirect:/back/article";
    }
}
