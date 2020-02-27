package com.zhuqiu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuqiu
 * @date 2020/2/1
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = -7475565351945650420L;
    private Integer categoryId;

    private String categoryName;

    private Integer superCategoryId;

    /**
     * 以下为非数据库字段
     */

    private Integer articleCount;

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
