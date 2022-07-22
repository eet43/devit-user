package com.devit.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ResponseMessageDetails {

    private Date timestamp;
    private String message;
    private int httpStatus;
    private String path;
}