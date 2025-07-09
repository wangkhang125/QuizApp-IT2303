package com.thk.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Choice {
    private int id;
    private String content;
    private boolean correct;

    public Choice(int id, String content, boolean correct) {
        this.id = id;
        this.content = content;
        this.correct = correct;
    }

    public Choice(String content, boolean correct) {
        this.content = content;
        this.correct = correct;
    }
}
