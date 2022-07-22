package com.devit.user.service;

import com.devit.user.entity.Resume;
import com.devit.user.entity.User;
import com.devit.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UUID saveUser(User user) {

        log.info("User(service) : 새로운 유저를 저장합니다. {}", user);

        return userRepository.save(user);
    }


    @Transactional
    public void deleteUser(UUID userId) {

        log.info("User(service) : {} 해당 유저를 삭제합니다.", userId);

        userRepository.deleteUser(userId);
    }

    public User findUser(UUID userId) {

        log.info("User(service) : {} 해당 유저를 조회합니다.", userId);

        return userRepository.findByUUID(userId);
    }

    public Resume findResume(UUID userId) {

        log.info("User(service) : {} 해당 유저의 이력서만 조회합니다.", userId);

        return userRepository.findResume(userId);
    }

    public Object findUserName(UUID userId) {
        return userRepository.findUserName(userId);
    }

}
