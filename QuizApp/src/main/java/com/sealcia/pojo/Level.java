package com.sealcia.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Level {
    private int id;
    private String name;
    private String note;

    @Override
    public String toString() {
        return this.name;
    }
}
