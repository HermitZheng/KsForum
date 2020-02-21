package com.zhuqiu.enums;

/**
 * @author zhuqiu
 * @date 2020/2/20
 */

public enum  Status {


    DRAFT(0, "草稿"),
    PUBLISH(1, "已发布"),
    TOP(2, "置顶"),
    Notice(3, "公告");



    private Integer code;

    private String status;

    private Status(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
