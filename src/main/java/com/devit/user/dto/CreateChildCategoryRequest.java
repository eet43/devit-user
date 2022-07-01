package com.devit.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreateChildCategoryRequest {
    private String name; //카테고리 이름
    private String parentName; //부모카테고리 이름
}
