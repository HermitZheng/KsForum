package com.zhuqiu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhuqiu
 * @date 2020/2/11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> implements Serializable {


    private static final long serialVersionUID = -9075611397573426736L;
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回的具体内容
     */
    private T data;

    public JsonResult fail() {
        return new JsonResult(1, "操作失败", null);
    }

    public JsonResult fail(String msg) {
        return new JsonResult(1, msg, null);
    }

    public JsonResult ok() {
        return new JsonResult(0, "操作成功", null);
    }


    public JsonResult ok(T data) {
        return new JsonResult(0, "操作成功", data);
    }
}
