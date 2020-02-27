package com.zhuqiu.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论（回复—）
 * @author zhuqiu
 * @date 2020/2/1
 */

@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -5329217357208401878L;
    private Integer commentId;

    private String commentContent;

    // 上级评论Id
    private Integer superId;

    // 上级评论者Name
    private String superAuthorName;

    private Integer articleId;

    private String articleTitle;

    private Integer commentAuthorId;

    private String commentAuthorName;

    private String commentAuthorAvatar;

    private Date commentCreateTime;

    /**
     * 一下为非数据库字段
     */

    private Article article;


}
