package com.devit.user.repository;

import com.devit.user.entity.Career;
import com.devit.user.entity.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CareerRepository {
    private final EntityManager em;

    public Career save(Career career) {
        em.persist(career);
        return career;
    }

}
