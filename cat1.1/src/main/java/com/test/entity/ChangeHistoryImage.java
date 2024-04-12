package com.test.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeHistoryImage {
    private String modifyTime;
    private byte[] oldImage;
    private byte[] newImage;
    private boolean isAdmin;

    public ChangeHistoryImage(String modifyTime, byte[] oldImage, byte[] newImage, boolean isAdmin) {
        this.modifyTime = modifyTime;
        this.oldImage = oldImage;
        this.newImage = newImage;
        this.isAdmin = isAdmin;
    }
}
