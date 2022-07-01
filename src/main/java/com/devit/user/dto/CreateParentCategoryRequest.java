package com.devit.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateParentCategoryRequest {
    private String name; //카테고리 이름
}
