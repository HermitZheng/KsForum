package com.zhuqiu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author zhuqiu
 * @date 2020/2/1
 */

@Data
public class User implements Serializable {

    private static final long serialVersionUID = 6450048280593614339L;
    private Integer userId;

    private String userName;

    private String userPass;

    private String userNickname;

    private String userProfile;

    private Date userRegisterTime;

    private String userAvatar;

    /**
     *  以下为非数据库字段
     */

    private Integer userFavoriteCount;

    private Integer userArticleCount;

    private Integer userCommentCount;


}
