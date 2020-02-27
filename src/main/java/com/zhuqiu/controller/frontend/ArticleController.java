package com.zhuqiu.controller.frontend;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuqiu.dto.ArticleParam;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/21
 */

@Controller
@RequestMapping("/home/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleFunctionService functionService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;


    @ResponseBody
    @RequestMapping("/get")
    public String getArticleList(){
        HashMap<String, Object> criteria = new HashMap<>();
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        criteria.put("articleStatus", statusList);
        List<Article> articleList = articleService.listAllNotWithContent(criteria);

        String json = JSON.toJSONString(articleList);

        System.out.println(json);
        return json;
    }


    @RequestMapping("/search")
    public String search(HttpServletRequest request, Model model){
        String articleTitle = request.getParameter("articleTitle");
        HashMap<String, Object> criteria = new HashMap<>();
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        criteria.put("articleStatus", statusList);
        criteria.put("keywords", articleTitle);

        Integer pageIndex = 1;
        Integer pageSize = 15;
        model.addAttribute("pageUrlPrefix", "/home/article/page");
        PageInfo<Article> articlePageInfo = functionService.pageArticle(pageIndex, pageSize, criteria);

        model.addAttribute("articleList", articlePageInfo);

        sideItem(model);

        return "Home/Article/index";
    }


    @RequestMapping("/page/{pageIndex}")
    public String index(@PathVariable("pageIndex") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "15") Integer pageSize,
                        Model model) {
        HashMap<String, Object> criteria = new HashMap<>();
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        criteria.put("articleStatus", statusList);
        model.addAttribute("pageUrlPrefix", "/home/article/page");
        PageInfo<Article> articlePageInfo = functionService.pageArticle(pageIndex, pageSize, criteria);

        model.addAttribute("articleList", articlePageInfo);

        sideItem(model);

        return "/Home/Article/index";
    }

    @RequestMapping("/{articleId}")
    public String article(@PathVariable("articleId") Integer articleId,
                          Model model, HttpSession session){
        if (session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            List<Integer> idList = userService.listFavoriteArticleId(user.getUserId());
            model.addAttribute("isFav", idList.contains(articleId));
        }

        Article article = articleService.findArticleById(articleId);
        User user = userService.findUserById(article.getArticleUserId());
        article.setUser(user);
        List<Category> categoryList = categoryService.listCategoryByArticleId(articleId);
        article.setCategoryList(categoryList);
        model.addAttribute("article", article);

        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList", commentList);



        sideItem(model);

        return "Home/Article/detail";
    }

    @RequestMapping("/write")
    public String write(Model model){
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        return "/Home/Article/write";
    }

    @RequestMapping("/insertSubmit")
    public String insertSubmit(HttpSession session, ArticleParam param){
        User user = (User) session.getAttribute("user");
        Article article = new Article();
        if (user != null) {
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(param.getArticleTitle());
        article.setArticleContent(param.getArticleContent());
        Integer status = param.getArticleStatus();
        article.setArticleStatus(status);
        Date date = new Date();
        if (status == 1) {        //为1则发布， 为0则为草稿
            article.setArticleCreateTime(date);
        }
        article.setArticleUpdateTime(date);

        List<Category> categoryList = new ArrayList<>();
        if (param.getArticleParentCategoryId() != null){
            categoryList.add(new Category(param.getArticleParentCategoryId()));
            if (param.getArticleChildCategoryId() != null){
                categoryList.add(new Category(param.getArticleChildCategoryId()));
            }
        }
        article.setCategoryList(categoryList);

        articleService.insertArticle(article);

        return "redirect:/home/user/index";
    }


    @RequestMapping("/delete/{articleId}")
    public String delete(@PathVariable("articleId") Integer articleId,
                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        Article article = articleService.findArticleById(articleId);
        if (!user.getUserId().equals(article.getArticleUserId())){
            return "redirect:/home/user/index";
        }
        articleService.deleteArticle(articleId);
        return "redirect:/home/user/index";
    }


    @RequestMapping("/edit/{articleId}")
    public String edit(@PathVariable("articleId") Integer articleId, Model model,
                       HttpSession session){
        User user = (User) session.getAttribute("user");
        Article article = articleService.findArticleById(articleId);
        if (!article.getArticleUserId().equals(user.getUserId())){
            return "redirect:/home/index";
        }
//        System.out.println(article);
        model.addAttribute("article", article);
        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);
        return "/Home/Article/edit";
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
        if (original == 0 && status == 1) {        //为1则发布， 为0则为草稿
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
        return "redirect:/home/user/index";
    }

    public void sideItem(Model model){
        HashMap<String, Object> notice = new HashMap<>();
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(3);
        notice.put("articleStatus", statusList);
        List<Article> noticeList = articleService.listAllNotWithContent(notice);
        model.addAttribute("noticeList", noticeList);

        List<Category> allCategory = categoryService.listCategoryWithArticleCount();
        model.addAttribute("allCategory", allCategory);
    }

}
