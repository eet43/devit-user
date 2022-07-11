package com.devit.user.service;

import com.devit.user.entity.Resume;
import com.devit.user.entity.User;
import com.devit.user.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
//    public UUID saveResume(Resume resume) {
//
//    }
    public Resume findByUser(User user) {
        return resumeRepository.findByUser(user);
    }
}
