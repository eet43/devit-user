package com.devit.user.dto;

import com.devit.user.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Arrays;

@Getter @AllArgsConstructor
public class EditCareerDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate; //시작 날짜

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; //종료 날짜

    private String careerStatus; //진행 중인지, 종료한 것인지 ?

    private String office; //회사 이름 최대 15글자

    private String job; //학과 이름 최대 15글자

    private String content; //추가 자기소개

    public EditCareerDto() {

    }

    public static Status of(String code) {
        return Arrays.stream(Status.values())
                .filter(r -> r.getLabel().equals(code))
                .findAny()
                .orElse(Status.ING);
    }
}
