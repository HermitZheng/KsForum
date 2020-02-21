package com.zhuqiu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuqiu
 * @date 2020/2/10
 */

@Data
public class ArticleParam implements Serializable {

    private static final long serialVersionUID = -2398835510883686500L;
    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleStatus;
}
