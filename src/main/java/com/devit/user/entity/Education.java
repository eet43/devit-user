package com.devit.user.entity;

import com.devit.user.util.Timestamped;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Education extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)", name = "education_id")
    private UUID educationId; //이력서 고유 id 값

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume; //매칭되는 이력서 다대일

    @Column(name = "start_date")
    private LocalDate startDate; //시작 날짜

    @Column(name = "end_date")
    private LocalDate endDate; //종료 날짜

    @Enumerated(value = EnumType.STRING)
    private Status educationStatus; //진행 중인지, 종료한 것인지 ?

    @Column(nullable = false, length = 15)
    private String university; //학교 이름 최대 15글자

    @Column(nullable = false, length = 20)
    private String department; //학과 이름 최대 20글자

    private String content; //추가 자기소개



    /* 생성 메서드 */
    public static Education createEducation(Resume resume, LocalDate startDate, LocalDate endDate, Status educationStatus
            , String university, String department, String content) {
        Education education = new Education();
        education.resume = resume;
        education.startDate = startDate;
        education.endDate = endDate;
        education.educationStatus = educationStatus;
        education.university = university;
        education.department = department;
        education.content = content;

        return education;
    }
}
