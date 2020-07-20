package com.fastcampus.admin.repository;

import com.fastcampus.admin.FastcampusStudyadminApplicationTests;
import com.fastcampus.admin.model.entity.CourseDetail;
import com.fastcampus.admin.respository.CourseDetailRepository;
import com.fastcampus.admin.respository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("CourseDetail repository 테스트")
public class CourseDetailRepositoryTest extends FastcampusStudyadminApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Test
    @Transactional
    public void create(){
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setTitle("Spring boot project");
        courseDetail.setContent("Spring boot project 학습하기 1");
        courseDetail.setCourse(courseRepository.getOne(2L));
        courseDetail.setCreatedAt(LocalDateTime.now());
        courseDetail.setCreatedBy("admin");

        CourseDetail newCourseDetail = courseDetailRepository.save(courseDetail);
        Assertions.assertNotNull(newCourseDetail);

    }

    @Test
    @Transactional
    public void read(){
        Optional<CourseDetail> courseDetail = courseDetailRepository.findById(1L);
        Assertions.assertTrue(courseDetail.isPresent());

        courseDetail.ifPresent(i ->{
            System.out.println(i.getTitle());
            System.out.println(i.getContent());
            System.out.println(i.getCourse().getTitle());
        });

    }

    @Test
    @Transactional
    public void update(){
        Optional<CourseDetail> courseDetail = courseDetailRepository.findById(2L);

        courseDetail.ifPresent(selectCourseDetail ->{
            selectCourseDetail.setTitle("JAVA boot project");
            selectCourseDetail.setContent("JAVA boot project 학습하기 2");
            selectCourseDetail.setUpdatedAt(LocalDateTime.now());
            selectCourseDetail.setUpdatedBy("update method()");

            courseDetailRepository.save(selectCourseDetail);
        });

    }

    @Test
    @Transactional
    public void delete(){

        Optional<CourseDetail> courseDetail = courseDetailRepository.findById(3L);

        Assertions.assertTrue(courseDetail.isPresent());

        courseDetail.ifPresent(selectCourseDetail ->{
            courseDetailRepository.delete(selectCourseDetail);
        });

        Optional<CourseDetail> deleteCourseDetail = courseDetailRepository.findById(3L);
        Assertions.assertFalse(deleteCourseDetail.isPresent());

    }
}
