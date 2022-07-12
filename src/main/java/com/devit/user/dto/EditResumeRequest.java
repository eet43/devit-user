package com.devit.user.dto;

import com.devit.user.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EditResumeRequest {

    @NotNull
    private Gender gender; //성별

    @NotNull
    private int year; //출생년도

    @NotNull
    private String phone_number; //유저 핸드폰 번호

    private String introduce; //자기소개

    @NotNull
    private String categoryName; //현재 직종 카테고리 (서버파트 => 스프링)

    private List<Education> educations = new ArrayList<>(); //학력사항 추가 가능 일대다
    private List<Career> careers = new ArrayList<>(); //경력사항 추가 가능 일대다
    private List<Award> awards = new ArrayList<>(); //수상 및 활동 추가 가능 일대다
}
