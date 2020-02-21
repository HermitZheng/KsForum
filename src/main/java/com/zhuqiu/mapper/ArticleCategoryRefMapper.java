package com.zhuqiu.mapper;



import com.zhuqiu.pojo.ArticleCategoryRef;
import com.zhuqiu.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章分类关联表Mapper
 * @author zhuqiu
 * @date 2020/2/2
 */
@Repository
@Mapper
public interface ArticleCategoryRefMapper {
    
    /**
     * 添加文章和分类关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    int insertRecord(ArticleCategoryRef record);

    /**
     * 根据分类ID删除记录
     * @param categoryId 分类ID
     * @return 影响行数
     */
    int deleteByCategoryId(Integer categoryId);

    /**
     * 根据文章ID删除记录
     * @param articleId 文章ID
     * @return 影响行数
     */
    int deleteByArticleId(Integer articleId);

    /**
     * 根据分类ID统计文章数
     * @param categoryId 分类ID
     * @return 文章数量
     */
    Integer countArticleByCategoryId(Integer categoryId);


    /**
     * 根据文章ID查询分类ID
     *
     * @param articleId 文章ID
     * @return 分类ID列表
     */
    List<Integer> listCategoryIdByArticleId(Integer articleId);

    /**
     * 根据分类ID查询文章ID
     *
     * @param categoryId 分类ID
     * @return 文章ID列表
     */
    List<Integer> listArticleIdByCategoryId(Integer categoryId);

    /**
     * 根据文章ID获得分类列表
     *
     * @param articleId 文章ID
     * @return 分类列表
     */
    List<Category> listCategoryByArticleId(Integer articleId);

}