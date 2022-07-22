package com.devit.user.repository;

import com.devit.user.entity.Award;
import com.devit.user.entity.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EducationRepository {
    private final EntityManager em;

    public Education save(Education education) {
        em.persist(education);
        return education;
    }

}
