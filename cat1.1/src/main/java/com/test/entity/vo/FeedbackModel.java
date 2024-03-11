package com.test.entity.vo;

public class FeedbackModel {
    private Integer code;  // 状态码
    private String msg = "成功！";  // 提示信息
//    private Object object; // 回显对象

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
//    public Object getObject() {
//        return object;
//    }
//    public void setObject(Object object) {
//        this.object = object;
//    }
}
