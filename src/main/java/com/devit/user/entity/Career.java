package com.devit.user.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)", name = "career_id")
    private UUID careerId; //이력서 고유 id 값

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume; //매칭되는 이력서 다대일

    @Column(name = "start_date")
    private LocalDate startDate; //시작 날짜

    @Column(name = "end_date")
    private LocalDate endDate; //종료 날짜

    @Enumerated(value = EnumType.STRING)
    private Status careerStatus; //진행 중인지, 종료한 것인지 ?

    @Column(nullable = false, length = 15)
    private String office; //회사 이름 최대 15글자

    @Column(nullable = false, length = 15)
    private String job; //학과 이름 최대 15글자

    private String content; //추가 자기소개
}
