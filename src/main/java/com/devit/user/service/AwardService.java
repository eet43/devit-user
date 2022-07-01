package com.devit.user.service;

import com.devit.user.entity.Award;
import com.devit.user.repository.AwardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AwardService {
    private final AwardRepository awardRepository;

    public Award saveAward(Award award) {
        return awardRepository.save(award);
    }

}
