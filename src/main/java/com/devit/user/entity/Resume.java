package com.devit.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고유 식별자 값

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, unique = true, columnDefinition = "BINARY(16)", name = "resume_id")
    private UUID resumeId;

    @OneToOne(mappedBy = "resume")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    

    private boolean gender;



}
