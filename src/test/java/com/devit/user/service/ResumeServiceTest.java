package com.devit.user.service;

import com.devit.user.dto.EditResumeRequest;
import com.devit.user.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) //JUnit 5 테스트
@SpringBootTest
@Transactional
class ResumeServiceTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;


    @Test
    public void 이력서_수정() throws Exception {

        UUID userId = UUID.fromString("ea579e47-fcff-40df-8cf7-1bc3136a584d");
        Resume resume = userService.findResume(userId);

        //given
        Category category1 = new Category();
        category1.setName("백엔드");
        category1.setDepth(1L);
        categoryService.saveCategory(category1);


        Category category2 = new Category();
        category2.setName("Spring");
        category2.setDepth(2L);
        category2.addParent(category1);
        categoryService.saveCategory(category2);

        System.out.println("================카테고리 저장 끝=================");



        List<Education> educationList = new ArrayList<>();
        List<Award> awardList = new ArrayList<>();

        Education education = Education.createEducation(resume, LocalDate.now(), LocalDate.now(), Status.DONE, "고려대",
                "과학기술대학", "졸업");
        Award award1 = Award.createAward(resume, LocalDate.now(), LocalDate.now(), "먹기대회",
               "장려상", "얏호");
        Award award2 = Award.createAward(resume, LocalDate.now(), LocalDate.now(), "코딩대회",
                "대상", "나이스");

        educationList.add(education);
        awardList.add(award1);
        awardList.add(award2);

        System.out.println("================데이터 저장 끝=================");

        EditResumeRequest request = new EditResumeRequest(Gender.MAN, 1998, "01012345678", "",
                category2.getName(), educationList, null, awardList);


        //when
        Resume save = resumeService.save(request, userId);

        //then
        System.out.println(save);
    }
}