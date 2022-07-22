package com.devit.user.service;

import com.devit.user.dto.EditAwardDto;
import com.devit.user.dto.EditCareerDto;
import com.devit.user.dto.EditEducationDto;
import com.devit.user.dto.EditResumeRequest;
import com.devit.user.entity.*;
import com.devit.user.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final AwardRepository awardRepository;
    private final EducationRepository educationRepository;
    private final CareerRepository careerRepository;

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public Resume save(EditResumeRequest request, UUID userId) {

        Resume resume = userRepository.findResume(userId);

        log.info("Resume(service) : {} 해당 유저의 이력서를 조회합니다.", resume);


        EditEducationDto eDto = request.getEducations();
        EditAwardDto aDto = request.getAwards();
        EditCareerDto cDto = request.getCareers();

        Education education = Education.editEducation(resume.getEducations(), eDto.getStartDate(), eDto.getEndDate(), eDto.of(eDto.getEducationStatus()),
                eDto.getUniversity(), eDto.getDepartment(), eDto.getContent());

        log.info("Education(service) : {} 해당 유저의 교육사항을 조회합니다.", education);


        Career career = Career.editCareer(resume.getCareers(), cDto.getStartDate(), cDto.getEndDate(), cDto.of(cDto.getCareerStatus()),
                cDto.getOffice(), cDto.getJob(), cDto.getContent());

        log.info("Career(service) : {} 해당 유저의 개발사항을 조회합니다.", career);


        Award award = Award.editAward(resume.getAwards(), aDto.getStartDate(), aDto.getEndDate(), aDto.getCompetition(), aDto.getAwards(), aDto.getContent());
        //dirty check 가능

        log.info("Award(service) : {} 해당 유저의 수상사항을 조회합니다.", award);


        Category findCategory = categoryRepository.findByName(request.getCategoryName());
        Gender gender = EditResumeRequest.of(request.getGender());

        return Resume.editResume(resume, gender, request.getYear(), request.getPhone_number(), request.getIntroduce(),
                findCategory, education, career, award);
    }
    public Resume findByUser(UUID userId) {
        return resumeRepository.findByUser(userId);
    }
}
