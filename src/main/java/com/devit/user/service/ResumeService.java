package com.devit.user.service;

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


    public Resume save(EditResumeRequest editResumeRequest, UUID userId) {

        Resume resume = userRepository.findResume(userId);

        //Null 값 들어왔을 시 비워주기

        List<Education> educationList = Optional.ofNullable(editResumeRequest.getEducations()).orElse(Collections.emptyList())
                .stream()
                .map(educationRepository::save)
                .collect(Collectors.toList());
        List<Award> awardList = Optional.ofNullable(editResumeRequest.getAwards()).orElse(Collections.emptyList())
                .stream()
                .map(awardRepository::save)
                .collect(Collectors.toList());
        List<Career> careerList = Optional.ofNullable(editResumeRequest.getCareers()).orElse(Collections.emptyList())
                .stream()
                .map(careerRepository::save)
                .collect(Collectors.toList());

        Category findCategory = categoryRepository.findByName(editResumeRequest.getCategoryName());

        return Resume.editResume(resume, editResumeRequest.getGender(), editResumeRequest.getYear(), editResumeRequest.getPhone_number(), editResumeRequest.getIntroduce(),
                findCategory, educationList, careerList, awardList);
    }
    public Resume findByUser(UUID userId) {
        return resumeRepository.findByUser(userId);
    }
}
