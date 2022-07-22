package com.devit.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @Column(unique = true, columnDefinition = "BINARY(16)", name = "career_id")
    private UUID careerId; //이력서 고유 id 값

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume; //매칭되는 이력서 다대일

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate; //시작 날짜

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; //종료 날짜

    @Enumerated(value = EnumType.STRING)
    private Status careerStatus; //진행 중인지, 종료한 것인지 ?

    @Column(length = 15)
    private String office; //회사 이름 최대 15글자

    @Column(length = 15)
    private String job; //학과 이름 최대 15글자

    private String content; //추가 자기소개


    public static Career createDefaultCareer(Resume resume) {
        Career career = new Career();
        career.careerId = UUID.randomUUID();
        career.resume = resume;
        return career;
    }

    /* 생성 메서드 */
    public static Career editCareer(Career career, LocalDate startDate, LocalDate endDate, Status careerStatus
    , String office, String job, String content) {
        career.startDate = startDate;
        career.endDate = endDate;
        career.careerStatus = careerStatus;
        career.office = office;
        career.job = job;
        career.content = content;

        return career;
    }
}
