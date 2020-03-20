package com.zhuqiu.controller.frontend;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuqiu.dto.CategoryTree;
import com.zhuqiu.pojo.Article;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.service.ArticleFunctionService;
import com.zhuqiu.service.ArticleService;
import com.zhuqiu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/24
 */

@Controller
@RequestMapping("/home/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleFunctionService functionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("")
    public String goIndex(Model model){
        List<CategoryTree> treeList;
        List<Category> categoryList;

        if (redisTemplate.opsForValue().get("categoryList") != null) {
            categoryList = (List<Category>) redisTemplate.opsForValue().get("categoryList");
        } else {
            categoryList = categoryService.listCategory();
            redisTemplate.opsForValue().set("categoryList", categoryList);
        }

        if (redisTemplate.opsForValue().get("categoryTree") != null) {
            treeList = (List<CategoryTree>) redisTemplate.opsForValue().get("categoryTree");
        } else {
            treeList = new ArrayList<>();
            for (Category category : categoryList) {
                if (category.getSuperCategoryId() == 0){
                    CategoryTree tree = new CategoryTree();
                    tree.setParentName(category.getCategoryName());
                    tree.setParentId(category.getCategoryId());
                    tree.setAllCount(articleService.countArticleByCategoryId(category.getCategoryId()));
                    List<CategoryTree.Child> childList = new ArrayList<>();
                    for (Category child : categoryList) {
                        if (child.getSuperCategoryId().equals(category.getCategoryId())){
                            CategoryTree.Child newChild = tree.new Child();
                            newChild.setChildId(child.getCategoryId());
                            newChild.setChildName(child.getCategoryName());
                            newChild.setArticleCount(articleService.countArticleByCategoryId(child.getCategoryId()));
                            childList.add(newChild);
                        }
                    }
                    tree.setChildList(childList);
                    treeList.add(tree);
                }
            }
            redisTemplate.opsForValue().set("categoryTree", treeList);
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("treeList", treeList);

//        System.out.println(JSON.toJSONString(treeList));
//        return JSON.toJSONString(treeList);
        return "Home/Category/index";
    }


    @ResponseBody
    @RequestMapping("/get")
    public String cateIndex(Model model){
        List<Category> categoryList = categoryService.listCategory();
        List<CategoryTree> treeList = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getSuperCategoryId() == 0){
                CategoryTree tree = new CategoryTree();
                tree.setParentId(category.getCategoryId());
                tree.setParentName(category.getCategoryName());
                tree.setAllCount(articleService.countArticleByCategoryId(category.getCategoryId()));
                List<CategoryTree.Child> childList = new ArrayList<>();
                for (Category child : categoryList) {
                    if (child.getSuperCategoryId().equals(category.getCategoryId())){
                        CategoryTree.Child newChild = tree.new Child();
                        newChild.setChildId(child.getCategoryId());
                        newChild.setChildName(child.getCategoryName());
                        newChild.setArticleCount(articleService.countArticleByCategoryId(child.getCategoryId()));
                        childList.add(newChild);
                    }
                }
                tree.setChildList(childList);
                treeList.add(tree);
            }
        }
//        model.addAttribute("categoryList", categoryList);
//        model.addAttribute("treeList", treeList);

        System.out.println(JSON.toJSONString(treeList));
        return JSON.toJSONString(treeList);
//        return "Home/Category/index";
    }

    @RequestMapping("/{categoryId}")
    public String findByCate(@PathVariable("categoryId") Integer categoryId,
                             Model model){

        HashMap<String, Object> criteria = new HashMap<>();
        ArrayList<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        criteria.put("articleStatus", statusList);
        criteria.put("categoryId", categoryId);

        Integer pageIndex = 1;
        Integer pageSize = 15;
        model.addAttribute("pageUrlPrefix", "/home/article/page");
        PageInfo<Article> articlePageInfo = functionService.pageArticle(pageIndex, pageSize, criteria);

        model.addAttribute("articleList", articlePageInfo);

        sideItem(model);

        return "Home/Article/index";
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
