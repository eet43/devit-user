package com.devit.user.entity;

import lombok.Getter;

@Getter
public enum Gender {
    MAN("MAN"),
    WOMAN("WOMAN");

    private String label;

    Gender(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

}
