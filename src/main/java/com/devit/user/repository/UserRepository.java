package com.devit.user.repository;

import com.devit.user.entity.Resume;
import com.devit.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;


    public UUID save(User user) {
        em.persist(user);
        return user.getUserId();
    }

    public User findByUUID(UUID userId) {
        User findUser = em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();

        return findUser;
    }

    public void deleteUser(UUID userId) {
        em.createQuery("delete from User u where u.userId = :userId")
                .setParameter("userId", userId);
    }

    public Resume findResume(UUID userId) {
        return em.createQuery("select u.resume from User u where u.userId = :userId", Resume.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public Object findUserName(UUID userId) {
        return em.createQuery("select u.name from User u where u.userId = :userId")
                .setParameter("userId", userId)
                .getSingleResult();
    }


}
