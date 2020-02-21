package com.zhuqiu.controller.frontend;

import com.github.pagehelper.PageInfo;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.pojo.Comment;
import com.zhuqiu.pojo.User;
import com.zhuqiu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @RequestMapping("/page/{pageIndex}")
    public String index(@PathVariable("pageIndex") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "15") Integer pageSize,
                        Model model) {
        HashMap<String, Object> criteria = new HashMap<>();
        criteria.put("articleStatus", 1);
        model.addAttribute("pageUrlPrefix", "/home/article/page");
        PageInfo<Article> articlePageInfo = functionService.pageArticle(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", articlePageInfo);

        return "/Home/Article/index";
    }

    @RequestMapping("/{articleId}")
    public String article(@PathVariable("articleId") Integer articleId,
                          Model model){
        Article article = articleService.findArticleById(articleId);
        User user = userService.findUserById(article.getArticleUserId());
        article.setUser(user);
        List<Category> categoryList = categoryService.listCategoryByArticleId(articleId);
        article.setCategoryList(categoryList);
        model.addAttribute("article", article);

        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList", commentList);

        HashMap<String, Object> notice = new HashMap<>();
        notice.put("articleStatus", 3);
        List<Article> noticeList = articleService.listAllNotWithContent(notice);
        model.addAttribute("noticeList", noticeList);

        List<Category> allCategory = categoryService.listCategoryWithArticleCount();
        model.addAttribute("allCategory", allCategory);

        return "Home/Article/detail";
    }

}
