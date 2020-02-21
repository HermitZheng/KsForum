package com.zhuqiu.service;

import com.zhuqiu.pojo.Category;

import java.util.List;

/**
 * @author zhuqiu
 * @date 2020/2/4
 */
public interface CategoryService {

    /**
     * 获得分类总数
     *
     * @return
     */
    Integer countCategory();


    /**
     * 获得分类列表
     *
     * @return 分类列表
     */
    List<Category> listCategory();

    /**
     * 获得分类列表
     *
     * @return 分类列表
     */
    List<Category> listCategoryWithArticleCount();

    /**
     * 删除分类
     *
     * @param categoryId ID
     */

    void deleteCategory(Integer categoryId);

    /**
     * 根据id查询分类信息
     *
     * @param categoryId     ID
     * @return 分类
     */
    Category findCategoryById(Integer categoryId);

    /**
     * 添加分类
     *
     * @param category 分类
     * @return 分类
     */
    Category insertCategory(Category category);

    /**
     * 更新分类
     *
     * @param category 分类
     */
    void updateCategory(Category category);

    /**
     * 根据分类名获取分类
     *
     * @param categoryName 名称
     * @return 分类
     */
    List<Category> listCategoryByName(String categoryName);

    /**
     * 根据父分类找子分类
     *
     * @param categoryId 分类ID
     * @return 子分类列表
     */
    List<Category> listChildCategory(Integer categoryId);

    /**
     * 根据文章ID查询分类ID
     *
     * @param articleId 文章ID
     * @return 分类ID列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);

    /**
     * 根据文章ID获得分类列表
     *
     * @param articleId 文章ID
     * @return 分类列表
     */
    List<Category> listCategoryByArticleId(Integer articleId);
}
