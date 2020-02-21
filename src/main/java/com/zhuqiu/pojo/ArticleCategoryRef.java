package com.zhuqiu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章-分类 索引
 * @author zhuqiu
 * @date 2020/2/1
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategoryRef implements Serializable {

    private static final long serialVersionUID = 6139039369014886203L;
    private Integer articleId;

    private Integer categoryId;
}
