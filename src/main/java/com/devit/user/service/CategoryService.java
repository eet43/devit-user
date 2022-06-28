package com.devit.user.service;

import com.devit.user.entity.Category;
import com.devit.user.repository.CategoryRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category findCategory(Long id) {
        return categoryRepository.findOne(id);
    }
}
