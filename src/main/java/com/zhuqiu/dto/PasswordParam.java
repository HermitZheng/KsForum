package com.zhuqiu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhuqiu
 * @date 2020/2/23
 */
@Data
public class RePassword implements Serializable {

    private static final long serialVersionUID = -6745785868564627135L;
    private Integer userId;

    private Integer oldPass;

    private Integer newPass;

    private Integer rePass;
}
