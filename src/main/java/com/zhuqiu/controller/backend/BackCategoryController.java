package com.zhuqiu.controller.backend;

import com.zhuqiu.pojo.Category;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /**
     * 后台添加分类提交
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertCategorySubmit(Category category)  {
        categoryService.insertCategory(category);
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
        return "redirect:/back/category";
    }


}
