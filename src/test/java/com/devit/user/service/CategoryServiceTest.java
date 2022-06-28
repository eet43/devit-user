package com.devit.user.service;

import com.devit.user.entity.Category;
import com.devit.user.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class) //JUnit 5 테스트
@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger("Daisy 의 로그");


    @Test
    public void 카테고리_저장_및_조회() throws Exception {
        //given
        Category category1 = new Category();
        category1.setName("웹 개발");


        //when
        Long findParentId = categoryService.saveCategory(category1);

        //then
        assertThat(category1, is(categoryService.findCategory(findParentId)));
    }

    @Test
    public void 자식_카테고리_확인() throws Exception {
        Category category1 = new Category();
        category1.setName("웹 개발");

        Category category2 = new Category();
        category2.setName("React");
        category2.addParent(category1);

        Category category3 = new Category();
        category3.setName("JS");
        category3.addParent(category1);


        //when
        Long findParentId = categoryService.saveCategory(category1);
        Long findChildId1 = categoryService.saveCategory(category2);
        Long findChildId2 = categoryService.saveCategory(category3);



        //then
        List<Category> categories = categoryService.findCategory(findParentId).getChildren();
        for (Category category : categories) {
            logger.info("카테고리 이름 : " + category.getName());
//            assertThat(category, is(categoryService.findCategory(findChildId1)));
        }
    }

    @Test //에러 발생해야함
    public void 카테고리_이름_Null() throws Exception {
        //given
        Category category1 = new Category();

        //when
        Long findParentId = categoryService.saveCategory(category1);

        //then
        assertThat(category1, is(categoryService.findCategory(findParentId)));

    }

}