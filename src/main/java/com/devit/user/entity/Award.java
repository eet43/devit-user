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
@Getter @Setter
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @Column(unique = true, columnDefinition = "BINARY(16)", name = "award_id")
    private UUID awardId; //이력서 고유 id 값

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


    @Column(length = 15)
    private String competition; //대회 이름 최대 15글자

    @Column(length = 10)
    private String awards; //수상 내용 최대 10글자

    private String content; //추가 자기소개

    public static Award createDefaultAward(Resume resume) {
        Award award = new Award();
        award.awardId = UUID.randomUUID();
        award.resume = resume;
        return award;
    }


    /* 생성 메서드 */
    public static Award editAward(Award award, LocalDate startDate, LocalDate endDate
            , String competition, String awards, String content) {
        award.startDate = startDate;
        award.endDate = endDate;
        award.competition = competition;
        award.awards = awards;
        award.content = content;

        return award;
    }
}
