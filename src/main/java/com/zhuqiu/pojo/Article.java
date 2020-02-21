package com.zhuqiu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章正文
 * @author zhuqiu
 * @date 2020/2/1
 */

@Data
public class Article implements Serializable {

    private static final long serialVersionUID = -4781100837885075841L;
    private Integer articleId;

    private Integer articleUserId;

    private String articleTitle;

    private String articleContent;

    // 浏览量
    private Integer articleViewCount;

    // 点赞数
    private Integer articleLikeCount;

    //评论数
    private Integer articleCommentCount;

    // 收藏数
    private Integer articleFavoriteCount;


    private Date articleCreateTime;

    private Date articleUpdateTime;

    private Integer articleStatus;



    /**
     *  以下为非数据库字段
     */

    private User user;


//    private String category;

    private List<Category> categoryList;

//    private List<Tag> tagList;


}
