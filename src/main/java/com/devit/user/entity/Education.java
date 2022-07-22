package com.devit.user.entity;

import com.devit.user.util.Timestamped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter @Setter
public class Education extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @Column(unique = true, columnDefinition = "BINARY(16)", name = "education_id")
    private UUID educationId; //이력서 고유 id 값

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
    private Status educationStatus; //진행 중인지, 종료한 것인지 ?

    @Column(length = 15)
    private String university; //학교 이름 최대 15글자

    @Column(length = 20)
    private String department; //학과 이름 최대 20글자

    private String content; //추가 자기소개

    public static Education createDefaultEducation(Resume resume) {
        Education education = new Education();
        education.educationId = UUID.randomUUID();
        education.resume = resume;
        return education;
    }



    /* 생성 메서드 */
    public static Education editEducation(Education education, LocalDate startDate, LocalDate endDate, Status educationStatus
            , String university, String department, String content) {
        education.startDate = startDate;
        education.endDate = endDate;
        education.educationStatus = educationStatus;
        education.university = university;
        education.department = department;
        education.content = content;

        return education;
    }
}
