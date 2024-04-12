package com.test.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ChangeHistory {
    private String modifyTime;
    private List<String> modifyLocation;
    private Map<String, Object> oldContent;
    private Map<String, Object> newContent;
    private boolean isAdmin;
    private String remark;

    public ChangeHistory(String modifyTime, List<String> modifyLocation, Map<String, Object> oldContent, Map<String, Object> newContent, boolean isAdmin, String remark) {
        this.modifyTime = modifyTime;
        this.modifyLocation = modifyLocation;
        this.oldContent = oldContent;
        this.newContent = newContent;
        this.isAdmin = isAdmin;
        this.remark = remark;
    }
}
