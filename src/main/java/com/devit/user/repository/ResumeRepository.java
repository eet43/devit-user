package com.devit.user.repository;

import com.devit.user.entity.Resume;
import com.devit.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final EntityManager em;

    public UUID save(Resume resume) {
        em.persist(resume);
        return resume.getResumeId();
    }

    public Resume findByUser(User user) {
        return em.createQuery("select r from Resume r where r.user = :user", Resume.class)
                .setParameter("user", user)
                .getSingleResult();
    }

}
