package com.zhuqiu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuqiu
 * @date 2020/2/23
 */
@Data
public class PasswordParam implements Serializable {

    private static final long serialVersionUID = 7422885692762142885L;
    private Integer userId;

    private String oldPass;

    private String newPass;

    private String rePass;
}
