package com.fastcampus.admin.repository;


import com.fastcampus.admin.FastcampusStudyadminApplicationTests;
import com.fastcampus.admin.model.entity.Course;
import com.fastcampus.admin.model.entity.Student;
import com.fastcampus.admin.model.enumclass.CourseStatus;
import com.fastcampus.admin.respository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Course repository 테스트")
public class CourseRepositoryTest extends FastcampusStudyadminApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Transactional
    public void create(){
        Course course = new Course();
        course.setTitle("SPRING");
        course.setStatus(CourseStatus.OPEN);
        course.setTeacherName("Test02");
        course.setTeacherPhoneNumber("010-2222-2222");
        course.setTeacherEmail("Test02@gmail.com");
        course.setAmount(BigDecimal.valueOf(30000));
        course.setCreatedAt(LocalDateTime.now());
        course.setCreatedBy("admin");

        Course newCourse = courseRepository.save(course);
        Assertions.assertNotNull(newCourse);

    }

    @Test
    @Transactional
    public void read(){
        Optional<Course> course = courseRepository.findById(1L);
        Assertions.assertTrue(course.isPresent());

        course.ifPresent(i ->{
            System.out.println(i);
        });

    }

    @Test
    @Transactional
    public void update(){
        Optional<Course> course = courseRepository.findById(1L);

        course.ifPresent(selectCourse ->{
            selectCourse.setStatus(CourseStatus.OPEN);
            selectCourse.setUpdatedAt(LocalDateTime.now());
            selectCourse.setUpdatedBy("update method()");

            courseRepository.save(selectCourse);
        });

    }

    @Test
    @Transactional
    public void delete(){

        Optional<Course> course = courseRepository.findById(1L);

        Assertions.assertTrue(course.isPresent());

        course.ifPresent(selectCourse ->{
            courseRepository.delete(selectCourse);
        });

        Optional<Course> deleteCourse = courseRepository.findById(1L);
        Assertions.assertFalse(deleteCourse.isPresent());

    }

}
