package com.fastcampus.admin.repository;

import com.fastcampus.admin.FastcampusStudyadminApplicationTests;
import com.fastcampus.admin.model.entity.ApplyCourse;
import com.fastcampus.admin.model.entity.CourseDetail;
import com.fastcampus.admin.model.entity.Student;
import com.fastcampus.admin.model.enumclass.ApplyCourseStatus;
import com.fastcampus.admin.respository.ApplyCourseDetailRepository;
import com.fastcampus.admin.respository.ApplyCourseRepository;
import com.fastcampus.admin.respository.CourseRepository;
import com.fastcampus.admin.respository.StudentRepository;
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
@DisplayName("ApplyCourse repository 테스트")
public class ApplyCourseRepositoryTest extends FastcampusStudyadminApplicationTests {

    @Autowired
    private ApplyCourseRepository applyCourseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Transactional
    public void create(){
        ApplyCourse applyCourse = new ApplyCourse();
        applyCourse.setStatus(ApplyCourseStatus.COMPLETE);
        applyCourse.setProgressRate(10);
        applyCourse.setIsComplete(true);
        applyCourse.setExpireAt(LocalDateTime.of(2020,03,31,12,0));
        applyCourse.setStudent(studentRepository.getOne(1L));
        applyCourse.setCourse(courseRepository.getOne(2L));
        applyCourse.setCreatedAt(LocalDateTime.now());
        applyCourse.setCreatedBy("admin");

        ApplyCourse newCourseDetail = applyCourseRepository.save(applyCourse);
        Assertions.assertNotNull(newCourseDetail);
    }

    @Test
    @Transactional
    public void read(){
        Optional<ApplyCourse> applyCourse = applyCourseRepository.findById(1L);
        Assertions.assertTrue(applyCourse.isPresent());

        applyCourse.ifPresent(i ->{
            System.out.println(i.getStatus());
            System.out.println(i.getIsComplete());
            System.out.println(i.getStudent().getAccount());
        });

    }

    @Test
    @Transactional
    public void update(){
        Optional<ApplyCourse> applyCourse = applyCourseRepository.findById(2L);

        applyCourse.ifPresent(selectApplyCourse ->{
            selectApplyCourse.setStatus(ApplyCourseStatus.PROCEEDING);
            selectApplyCourse.setProgressRate(10);
            selectApplyCourse.setUpdatedAt(LocalDateTime.now());
            selectApplyCourse.setUpdatedBy("update method()");

            applyCourseRepository.save(selectApplyCourse);
        });

    }

    @Test
    @Transactional
    public void delete(){

        Optional<ApplyCourse> applyCourse = applyCourseRepository.findById(2L);

        Assertions.assertTrue(applyCourse.isPresent());

        applyCourse.ifPresent(selectApplyCourse ->{
            applyCourseRepository.delete(selectApplyCourse);
        });

        Optional<ApplyCourse> deleteApplyCourse = applyCourseRepository.findById(2L);
        Assertions.assertFalse(deleteApplyCourse.isPresent());

    }
}
