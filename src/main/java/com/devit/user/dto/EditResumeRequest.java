package com.devit.user.dto;

import com.devit.user.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class EditResumeRequest {

    private String gender; //성별

    private int year; //출생년도

    private String phone_number; //유저 핸드폰 번호

    private String introduce; //자기소개

    private String categoryName; //현재 직종 카테고리 (서버파트 => 스프링)

    private EditEducationDto educations;//학력사항 추가 가능 일대다
    private EditCareerDto careers; //경력사항 추가 가능 일대다
    private EditAwardDto awards; //수상 및 활동 추가 가능 일대다

    public EditResumeRequest() {

    }

    public static Gender of(String code) {
        return Arrays.stream(Gender.values())
                .filter(r -> r.getLabel().equals(code))
                .findAny()
                .orElse(Gender.MAN);
    }
}
