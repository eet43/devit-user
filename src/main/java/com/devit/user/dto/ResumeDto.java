package com.devit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ResumeDto implements Serializable {
    private UUID userUid;
    private String userName;
    private UUID boardUid;

    @Override
    public String toString() {
        return "Resume{" +
                "userUid='" + userUid + '\'' +
                ", userName='" + userName + '\'' +
                ", boardUid='" + boardUid + '\'' +
                '}';
    }
}