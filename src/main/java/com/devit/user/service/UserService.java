package com.devit.user.service;

import com.devit.user.entity.Resume;
import com.devit.user.entity.User;
import com.devit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UUID saveUser(User user) {
        return userRepository.save(user);
    }


    @Transactional
    public void deleteUser(UUID userId) {
        userRepository.deleteUser(userId);
    }

    public User findUser(UUID userId) {
        return userRepository.findByUUID(userId);
    }

    public Resume findResume(UUID userId) {
        return userRepository.findResume(userId);
    }

}
