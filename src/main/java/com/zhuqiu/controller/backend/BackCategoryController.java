package com.zhuqiu.controller.backend;

import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/10
 */

@Controller("backCategoryController")
@RequestMapping("/back/category")
public class BackCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 后台分类列表显示
     *
     * @return
     */
    @RequestMapping("")
    public String categoryList(Model model){
        List<Category> categoryList = categoryService.listCategoryWithArticleCount();
        model.addAttribute("categoryList", categoryList);
        return "/Back/Category/index";
    }

    @RequestMapping("/{categoryId}")
    public String cateArticles(Model model, @PathVariable("categoryId") Integer categoryId){
        HashMap<String, Object> criteria = new HashMap<>();
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        criteria.put("articleStatus", statusList);
        criteria.put("categoryId", categoryId);

        List<Article> articleList = articleService.listAllNotWithContent(criteria);

        model.addAttribute("articleList", articleList);

        return "/Back/Article/index";
    }

    /**
     * 后台添加分类提交
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertCategorySubmit(Category category)  {
        categoryService.insertCategory(category);

        redisTemplate.delete("categoryList");
        redisTemplate.delete("categoryTree");

        return "redirect:/back/category";
    }

    /**
     * 删除分类
     * @param categoryId
     * @return
     */
    @RequestMapping("/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") Integer categoryId){
        Integer count = articleService.countArticleByCategoryId(categoryId);
        if (count == 0){
            categoryService.deleteCategory(categoryId);
        }
        redisTemplate.delete("categoryList");
        redisTemplate.delete("categoryTree");

        return "redirect:/back/category";
    }

    /**
     * 跳转至编辑界面
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping("/edit/{categoryId}")
    public String edit(@PathVariable("categoryId") Integer categoryId, Model model){
        Category category = categoryService.findCategoryById(categoryId);
        model.addAttribute("category", category);

        List<Category> categoryList = categoryService.listCategoryWithArticleCount();
        model.addAttribute("categoryList", categoryList);
        return "/Back/Category/edit";
    }

    /**
     * 提交更新
     * @param category
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(Category category){
        categoryService.updateCategory(category);

        redisTemplate.delete("categoryList");
        redisTemplate.delete("categoryTree");

        return "redirect:/back/category";
    }


}
