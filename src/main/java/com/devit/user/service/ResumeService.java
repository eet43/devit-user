package com.devit.user.service;

import com.devit.user.dto.EditAwardDto;
import com.devit.user.dto.EditCareerDto;
import com.devit.user.dto.EditEducationDto;
import com.devit.user.dto.EditResumeRequest;
import com.devit.user.entity.*;
import com.devit.user.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
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
        EditEducationDto eDto = request.getEducations();
        EditAwardDto aDto = request.getAwards();
        EditCareerDto cDto = request.getCareers();

        Education education = Education.editEducation(resume.getEducations(), eDto.getStartDate(), eDto.getEndDate(), eDto.of(eDto.getEducationStatus()),
                eDto.getUniversity(), eDto.getDepartment(), eDto.getContent());

        Career career = Career.editCareer(resume.getCareers(), cDto.getStartDate(), cDto.getEndDate(), cDto.of(cDto.getCareerStatus()),
                cDto.getOffice(), cDto.getJob(), cDto.getContent());

        Award award = Award.editAward(resume.getAwards(), aDto.getStartDate(), aDto.getEndDate(), aDto.getCompetition(), aDto.getAwards(), aDto.getContent());
        //dirty check 가능


        //Null 값 들어왔을 시 비워주기

//        List<Education> educationList = Optional.ofNullable(editResumeRequest.getEducations()).orElse(Collections.emptyList())
//                .stream()
//                .map(dto -> {
//                    Status educationStatus = dto.of(dto.getEducationStatus());
//                    Education education = Education.editEducation(resume.get, dto.getStartDate(), dto.getEndDate(), educationStatus,
//                            dto.getUniversity(), dto.getDepartment(), dto.getContent());
//                    return educationRepository.save(education);
//                })
//                .collect(Collectors.toList());

//        List<Award> awardList = Optional.ofNullable(editResumeRequest.getAwards()).orElse(Collections.emptyList())
//                .stream()
//                .map(dto -> {
//                    Award award = Award.createAward(resume, dto.getStartDate(), dto.getEndDate(),
//                            dto.getCompetition(), dto.getAwards(), dto.getContent());
//                    return awardRepository.save(award);
//                })
//                .collect(Collectors.toList());
//
//        List<Career> careerList = Optional.ofNullable(editResumeRequest.getCareers()).orElse(Collections.emptyList())
//                .stream()
//                .map(dto -> {
//                    Status careerStatus = dto.of(dto.getCareerStatus());
//                    Career career = Career.createCareer(resume, dto.getStartDate(), dto.getEndDate(), careerStatus,
//                            dto.getOffice(), dto.getJob(), dto.getContent());
//                    return careerRepository.save(career);
//                })
//                .collect(Collectors.toList());

        Category findCategory = categoryRepository.findByName(request.getCategoryName());
        Gender gender = EditResumeRequest.of(request.getGender());

        return Resume.editResume(resume, gender, request.getYear(), request.getPhone_number(), request.getIntroduce(),
                findCategory, education, career, award);
    }
    public Resume findByUser(UUID userId) {
        return resumeRepository.findByUser(userId);
    }
}
