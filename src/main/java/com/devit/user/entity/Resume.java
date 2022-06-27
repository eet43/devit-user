package com.devit.user.entity;

import com.devit.user.util.Timestamped;
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
public class Resume extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)", name = "resume_id")
    private UUID resumeId; //이력서 고유 id 값

    @OneToOne(mappedBy = "resume")
    private User user;

    @Column(nullable = false, unique = true, length = 13)
    private String phone_number; //유저 핸드폰 번호

    private boolean gender;

    private int year; //출생년도

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; //현재 직종 카테고리 (서버파트 => 스프링)

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Education> educations = new ArrayList<>(); //학력사항 추가 가능 일대다

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Career> careers = new ArrayList<>(); //경력사항 추가 가능 일대다

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Career> awards = new ArrayList<>(); //수상 및 활동 추가 가능 일대다


}
