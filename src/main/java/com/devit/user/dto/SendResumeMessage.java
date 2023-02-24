package com.devit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SendResumeMessage {
    private UUID userId;
    private UUID boardId;
}
