package com.devit.user.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume; //매칭되는 이력서 다대일


    @Column(name = "start_date")
    private LocalDate startDate; //시작 날짜

    @Column(name = "end_date")
    private LocalDate endDate; //종료 날짜


    @Column(nullable = false, length = 15)
    private String competition; //대회 이름 최대 15글자

    @Column(nullable = false, length = 10)
    private String awards; //수상 내용 최대 10글자

    private String content; //추가 자기소개



    /* 생성 메서드 */
    public static Award createAward(Resume resume, LocalDate startDate, LocalDate endDate
            , String competition, String awards, String content) {
        Award award = new Award();
        award.resume = resume;
        award.startDate = startDate;
        award.endDate = endDate;
        award.competition = competition;
        award.awards = awards;
        award.content = content;

        return award;
    }
}
