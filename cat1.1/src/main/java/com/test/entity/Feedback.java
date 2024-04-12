package com.test.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feedback {
    private boolean state;
    private String message;

    public Feedback(boolean state, String message) {
        this.state = state;
        this.message = message;
    }
}
