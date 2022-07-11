package com.devit.user.controller;

import com.devit.user.dto.EditResumeRequest;
import com.devit.user.entity.Category;
import com.devit.user.entity.Education;
import com.devit.user.entity.User;
import com.devit.user.exception.NoResourceException;
import com.devit.user.service.CategoryService;
import com.devit.user.service.ResumeService;
import com.devit.user.service.UserService;
import com.devit.user.util.HttpStatusChangeInt;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * 1. 이력서 수정.
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;
    private final CategoryService categoryService;
    private final UserService userService;

    @PutMapping("/api/users/resumes")
    @ApiOperation(value = "이력서 수정", notes = "이력서를 수정합니다.")
    public ResponseEntity<?> editResume(@RequestHeader("Authorization") String data, @RequestBody @Valid EditResumeRequest request) throws NoResourceException {

        String[] chunks = data.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        JSONObject jsonObject = new JSONObject(payload);
        String sample = jsonObject.getString("uuid");
        UUID uuid = UUID.fromString(sample); //토큰 복호화 완료

        User findUser = userService.findUser(uuid);

//        request.getEducations().stream()
//                .filter(e->e!=null)
//                .map(e->{Education.createEducation(e.)})

        Category findCategory = categoryService.findCategoryByName(request.getCategoryName()); //카테고리 찾아오기

        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("Edited");
        String path = "api/users/resumes";
    }

}
