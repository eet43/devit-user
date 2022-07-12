package com.devit.user.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.io.Serializable;


@Data
public class CustomMessage implements Serializable{
    private String email;
    private String nickName;
    private UUID uuid;

    @Override
    public String toString() {
        return "{" +
                "email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
