package com.devit.user.controller;

import com.devit.user.dto.CreateChildCategoryRequest;
import com.devit.user.dto.CreateParentCategoryRequest;
import com.devit.user.dto.GetCategoryResponse;
import com.devit.user.entity.Category;
import com.devit.user.exception.NoResourceException;
import com.devit.user.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. 부모 카테고리 등록
 * 2. 자식 카테고리 등록
 * 3. 자식 카테고리들 조회
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/categories/parent") //1
    @ApiOperation(value = "부모 카테고리 등록", notes = "부모 카테고리를 등록합니다.")
    public ResponseEntity<?> saveParentCategory(@RequestBody CreateParentCategoryRequest request) throws NoResourceException {
        log.info("category : {} 부모 카테고리를 저장합니다.", request.getName());

        Category category = new Category();
        category.setName(request.getName());
        category.setDepth(1L);

        Long saveCategoryId = categoryService.saveCategory(category);

        log.info("category : {} 부모 카테고리 Id 를 반환합니다.", saveCategoryId);


        return ResponseEntity.ok().body(saveCategoryId);
    }

    @PostMapping("/categories/children") //2
    @ApiOperation(value = "자식 카테고리 등록", notes = "자식 카테고리를 등록합니다.")
    public ResponseEntity<?> saveChildCategory(@RequestBody CreateChildCategoryRequest request) throws NoResourceException {
        log.info("category : {} 자식 카테고리를 저장합니다.", request.getName());

        Category findParent = categoryService.findCategoryByName(request.getParentName());

        Category category = new Category();
        category.setName(request.getName());
        category.setDepth(2L);
        category.addParent(findParent);

        log.info("category : 부모와 자식 카테고리를 연결합니다.");


        Long saveCategoryId = categoryService.saveCategory(category);

        log.info("category : {} 자식 카테고리 id를 반환합니다.", saveCategoryId);


        return ResponseEntity.ok().body(saveCategoryId);
    }

    @GetMapping("/categories") //3
    @ApiOperation(value = "카테고리 조회", notes = "자식 카테고리를 조회합니다.")
    public ResponseEntity<?> getCategories() {

        log.info("category : 카테고리를 조회합니다.");

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
