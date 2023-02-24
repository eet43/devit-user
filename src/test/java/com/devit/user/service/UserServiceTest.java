package com.devit.user.service;

import com.devit.user.entity.Resume;
import com.devit.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class) //JUnit 5 테스트
@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void 유저_조회() throws Exception {
        //given
        UUID userId = UUID.fromString("ea579e47-fcff-40df-8cf7-1bc3136a584d");


        //when
        User user = userService.findUser(userId);

        //then
        assertThat(user.getUserId(), is(userId));
    }

}