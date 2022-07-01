package com.devit.user.repository;

import com.devit.user.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final EntityManager em;

    public Long save(Category category) {
        em.persist(category);
        return category.getId();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public Category findByName(String name) {
        return em.createQuery("select c from Category c where c.name = :name", Category.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Category> findParentCategories() {
        return em.createQuery("select c from Category c where c.depth = 1", Category.class)
                .getResultList();
    }

}
