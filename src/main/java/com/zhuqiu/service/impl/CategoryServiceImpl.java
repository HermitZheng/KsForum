package com.zhuqiu.service.impl;

import com.zhuqiu.mapper.ArticleCategoryRefMapper;
import com.zhuqiu.mapper.CategoryMapper;
import com.zhuqiu.pojo.Category;
import com.zhuqiu.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/4
 */

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private ArticleCategoryRefMapper categoryRefMapper;

    @Override
    public Integer countCategory() {
        Integer countCategory = null;
        try {
            countCategory = categoryMapper.countCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计分类失败, cause:{}", e);
        }
        return countCategory;
    }

    @Override
    public List<Category> listCategory() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.findAllCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取分类列表失败, cause:{}", e);

        }
        return categoryList;
    }

    @Override
    public List<Category> listCategoryWithArticleCount() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.findAllCategory();
            for (Category category : categoryList) {
                category.setArticleCount(categoryRefMapper.countArticleByCategoryId(category.getCategoryId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取分类列表失败, cause:{}", e);

        }
        return categoryList;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        try {
            categoryMapper.deleteCategoryById(categoryId);
            categoryRefMapper.deleteByCategoryId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除分类失败，cause:{}", e);
        }
    }

    @Override
    public Category findCategoryById(Integer categoryId) {
        Category category = null;
        try {
            category = categoryMapper.findCategoryById(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据id获取分类失败，categoryId:{}, cause:{}", categoryId, e);
        }
        return category;
    }

    @Override
    public Category insertCategory(Category category) {
        try {
            categoryMapper.insertCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("新增分类失败，category:{}, cause:{}", category, e);
        }
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        try {
            categoryMapper.updateCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新分类失败，category:{}, cause:{}", category, e);
        }
    }

    @Override
    public List<Category> listCategoryByName(String categoryName) {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.listCategoryByName(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据类名获取分类列表失败，categoryName:{}, cause:{}", categoryName, e);
        }
        return categoryList;
    }

    @Override
    public List<Category> listChildCategory(Integer categoryId) {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.listChildCategory(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据父类获取子类列表失败，categoryId:{}, cause:{}", categoryId, e);

        }
        return categoryList;
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        List<Integer> list = null;
        try {
            list = categoryRefMapper.listCategoryIdByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章id获取分类id列表失败，articleId:{}, cause:{}", articleId, e);
        }
        return list;
    }

    @Override
    public List<Category> listCategoryByArticleId(Integer articleId) {
        List<Category> list = null;
        try {
            list = categoryRefMapper.listCategoryByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章id获取分类列表失败，articleId:{}, cause:{}", articleId, e);
        }
        return list;
    }


}
