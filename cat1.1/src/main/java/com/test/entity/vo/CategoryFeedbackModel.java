package com.test.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryFeedbackModel {
    private Integer code = 200;  // 状态码
    private String msg = "成功！";  // 提示信息
    private Object object; // 回显对象
}
