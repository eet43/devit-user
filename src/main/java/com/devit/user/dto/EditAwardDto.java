package com.devit.user.dto;

import com.devit.user.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class EditAwardDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate; //시작 날짜

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; //종료 날짜

    private String competition; //대회 이름 최대 15글자

    private String awards; //수상 내용 최대 10글자

    private String content; //추가 자기소개

    public EditAwardDto() {

    }
}
