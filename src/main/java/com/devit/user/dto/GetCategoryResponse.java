package com.devit.user.dto;

import com.devit.user.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetCategoryResponse {
    private Long id;
    private String name;
    private List<Category> childCategories;
}
