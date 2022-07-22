package com.devit.user.entity;

import com.devit.user.util.Timestamped;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Resume extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)", name = "resume_id")
    private UUID resumeId; //이력서 고유 id 값

    @Enumerated(value = EnumType.STRING)
    private Gender gender; //성별

    private int year; //출생년도

    @Column(length = 13)
    private String phone_number; //유저 핸드폰 번호

    private String introduce; //자기소개


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; //현재 직종 카테고리 (서버파트 => 스프링)

    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL)
    private Education educations; //학력사항 추가 가능 일대일

    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL)
    private Career careers; //경력사항 추가 가능 일대일

    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL)
    private Award awards; //수상 및 활동 추가 가능 일대일

    /*생성 메서드*/
    /**
     * 1. 기본 생성자
     * 2. 수정해서 생긴 유효한 생성자
     */

    public static Resume createDefaultResume(User user) {
        Resume resume = new Resume();
        resume.resumeId = UUID.randomUUID();
        user.setResume(resume);

        return resume;
    }


    public static Resume editResume(Resume resume, Gender gender, int year, String phone_number, String introduce, Category category,
    Education educations, Career careers, Award awards) {
        resume.gender = gender;
        resume.year = year;
        resume.phone_number = phone_number;
        resume.introduce = introduce;
        resume.category = category;
        resume.educations = educations;
        resume.careers = careers;
        resume.awards = awards;

        return resume;
    }


}
