package com.zhuqiu.controller.frontend;

import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.service.ArticleFunctionService;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/19
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleFunctionService functionService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")
    public String home(Model model){
        HashMap<String, Object> top = new HashMap<>();
        top.put("articleStatus", 2);
        List<Article> topList = articleService.listAllNotWithContent(top);
        model.addAttribute("topList", topList);

        List<Article> articleByComment = functionService.listArticleByCommentCount(10);
        model.addAttribute("articleByComment", articleByComment);

        List<Article> articleByUpdate = functionService.getLastUpdateArticle(10);
        model.addAttribute("articleByUpdate", articleByUpdate);

        HashMap<String, Object> notice = new HashMap<>();
        notice.put("articleStatus", 3);
        List<Article> noticeList = articleService.listAllNotWithContent(notice);
        model.addAttribute("noticeList", noticeList);

        List<Category> allCategory = categoryService.listCategoryWithArticleCount();
        model.addAttribute("allCategory", allCategory);

        return "/Home/index";
    }

    @RequestMapping("/login")
    public String login(){

        return "/Home/login";
    }

    @ResponseBody
    @RequestMapping("/loginVerify")
    public String loginVerify(){

        return "";
    }

}
