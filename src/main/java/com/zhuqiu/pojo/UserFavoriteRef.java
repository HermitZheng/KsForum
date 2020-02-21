package com.zhuqiu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户-收藏 索引
 * @author zhuqiu
 * @date 2020/2/1
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFavoriteRef implements Serializable {

    private static final long serialVersionUID = -3254628479368944264L;
    private Integer userId;

    private Integer articleId;

}
