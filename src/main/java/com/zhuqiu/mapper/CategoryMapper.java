package com.zhuqiu.mapper;

import com.zhuqiu.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章分类信息
 * @author zhuqiu
 * @date 2020/2/1
 */

@Repository
@Mapper
public interface CategoryMapper {

    /**
     * 根据分类Id查找
     * @param categoryId
     * @return 类别
     */
    Category findCategoryById(Integer categoryId);

    /**
     * 查询所有类别
     * @return 类别列表
     */
    List<Category> findAllCategory();

    /**
     * 新增一个类别
     * @param category
     * @return 影响行数
     */
    int insertCategory(Category category);

    /**
     * 按Id删除类别
     * @param categoryId
     * @return 影响行数
     */
    int deleteCategoryById(Integer categoryId);

    /**
     * 更新类别信息
     * @param category
     * @return 影响行数
     */
    int updateCategory(Category category);

    /**
     * 查询分类总数
     * @return 分类总数
     */
    Integer countCategory();

    /**
     * 根据类名查找分类
     * @param categoryName
     * @return 分类列表
     */
    List<Category> listCategoryByName(String categoryName);

    /**
     * 根据父分类找子分类
     *
     * @param categoryId 分类ID
     * @return 子分类列表
     */
    List<Category> listChildCategory(Integer categoryId);
}
