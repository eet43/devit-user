package com.devit.user.service;

import com.devit.user.dto.EditResumeRequest;
import com.devit.user.entity.Award;
import com.devit.user.entity.Resume;
import com.devit.user.entity.User;
import com.devit.user.repository.AwardRepository;
import com.devit.user.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final AwardRepository awardRepository;

    public Resume save(EditResumeRequest editResumeRequest, UUID userId) {
        List<Award> awardList = editResumeRequest.getAwards().stream()
                .map(awardRepository::save)
                .collect(Collectors.toList());

    }
    public Resume findByUser(User user) {
        return resumeRepository.findByUser(user);
    }
}
