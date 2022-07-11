package com.devit.user.controller;

import com.devit.user.dto.response.ResponseDetails;
import com.devit.user.dto.response.ResponseProfileDetails;
import com.devit.user.entity.Resume;
import com.devit.user.entity.User;
import com.devit.user.exception.NoResourceException;
import com.devit.user.service.ResumeService;
import com.devit.user.service.UserService;
import com.devit.user.util.HttpStatusChangeInt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @ 유저 생성은 메시지 큐 이벤트 처리 방식으로 받기 때문에 API 제외 *
 * 1. 유저 프로필 조회 (이력서도 함께 조회)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResumeService resumeService;
    /*
    토큰 파싱해야함.
     */

    @GetMapping("/api/users")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String data) throws NoResourceException {

        String[] chunks = data.split("\\.");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        JSONObject jsonObject = new JSONObject(payload);
        String sample = jsonObject.getString("uuid");
        UUID uuid = UUID.fromString(sample);

        User findUser = userService.findUser(uuid);
        Resume findResume = resumeService.findByUser(findUser);

        int httpStatus = HttpStatusChangeInt.ChangeStatusCode("OK");
        String path = "api/users/";


        ResponseProfileDetails responseDetails = new ResponseProfileDetails(new Date(), findUser, findResume, httpStatus, path);
        return new ResponseEntity<>(responseDetails, HttpStatus.CREATED);
    }
}
