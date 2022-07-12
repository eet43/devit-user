package com.devit.user.repository;

import com.devit.user.entity.Award;
import com.devit.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AwardRepository {
    private final EntityManager em;

    public Award save(Award award) {
        em.persist(award);
        return award;
    }

}
