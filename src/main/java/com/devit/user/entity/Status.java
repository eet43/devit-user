package com.devit.user.entity;

import lombok.Getter;

@Getter
public enum Status {
    ING("ING"),
    DONE("DONE");

    private String label;

    Status(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
