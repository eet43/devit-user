package com.devit.user.controller;

import com.devit.user.dto.CreateChildCategoryRequest;
import com.devit.user.dto.CreateParentCategoryRequest;
import com.devit.user.dto.GetCategoryResponse;
import com.devit.user.entity.Category;
import com.devit.user.exception.NoResourceException;
import com.devit.user.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. 부모 카테고리 등록
 * 2. 자식 카테고리 등록
 * 3. 카테고리 조회
 */
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("api/users/categories/parent") //1
    public ResponseEntity<?> saveParentCategory(@RequestBody CreateParentCategoryRequest request) throws NoResourceException {
        Category category = new Category();
        category.setName(request.getName());
        category.setDepth(1L);

        Long saveCategoryId = categoryService.saveCategory(category);

        return ResponseEntity.ok().body(saveCategoryId);
    }

    @PostMapping("api/users/categories/children") //2
    public ResponseEntity<?> saveChildCategory(@RequestBody CreateChildCategoryRequest request) throws NoResourceException {

        Category findParent = categoryService.findCategoryByName(request.getParentName());

        Category category = new Category();
        category.setName(request.getName());
        category.setDepth(2L);
        category.addParent(findParent);

        Long saveCategoryId = categoryService.saveCategory(category);

        return ResponseEntity.ok().body(saveCategoryId);
    }

    @GetMapping("api/users/categories/") //3
    public ResponseEntity<?> getCategories() {

        List<Category> findParents = categoryService.findParentCategoires();
        List<GetCategoryResponse> collect = findParents.stream()
                .map(c -> new GetCategoryResponse(c.getId(), c.getName(), c.getChildren()))
                .collect(Collectors.toList());
        Result result = new Result(collect.size(), collect);

        return ResponseEntity.ok().body(result);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }


}
