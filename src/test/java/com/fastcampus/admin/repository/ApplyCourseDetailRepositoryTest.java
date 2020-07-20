package com.fastcampus.admin.repository;

import com.fastcampus.admin.FastcampusStudyadminApplicationTests;
import com.fastcampus.admin.model.entity.ApplyCourse;
import com.fastcampus.admin.model.entity.ApplyCourseDetail;
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
public class ApplyCourseDetailRepositoryTest extends FastcampusStudyadminApplicationTests {

    @Autowired
    private ApplyCourseRepository applyCourseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ApplyCourseDetailRepository applyCourseDetailRepository;

    @Test
    @Transactional
    public void create(){
        ApplyCourseDetail applyCourseDetail = new ApplyCourseDetail();
        applyCourseDetail.setApplyCourse(applyCourseRepository.getOne(1L));
        applyCourseDetail.setCreatedAt(LocalDateTime.now());
        applyCourseDetail.setCreatedBy("admin");

        ApplyCourseDetail newApplyCourseDetail = applyCourseDetailRepository.save(applyCourseDetail);
        Assertions.assertNotNull(newApplyCourseDetail);
    }

    @Test
    @Transactional
    public void read(){
        Optional<ApplyCourseDetail> applyCourseDetail = applyCourseDetailRepository.findById(1L);
        Assertions.assertTrue(applyCourseDetail.isPresent());

        applyCourseDetail.ifPresent(i ->{
            System.out.println(i.getApplyCourse());
        });

    }

    @Test
    @Transactional
    public void update(){
        Optional<ApplyCourseDetail> applyCourseDetail = applyCourseDetailRepository.findById(1L);

        applyCourseDetail.ifPresent(selectApplyCourseDetail ->{
            selectApplyCourseDetail.setApplyCourse(applyCourseRepository.getOne(2L));
            selectApplyCourseDetail.setUpdatedAt(LocalDateTime.now());
            selectApplyCourseDetail.setUpdatedBy("update method()");

            applyCourseDetailRepository.save(selectApplyCourseDetail);
        });

    }

    @Test
    @Transactional
    public void delete(){

        Optional<ApplyCourseDetail> applyCourseDetail = applyCourseDetailRepository.findById(3L);

        Assertions.assertTrue(applyCourseDetail.isPresent());

        applyCourseDetail.ifPresent(selectApplyCourseDetail ->{
            applyCourseDetailRepository.delete(selectApplyCourseDetail);
        });

        Optional<ApplyCourseDetail> deleteApplyCourseDetail = applyCourseDetailRepository.findById(3L);
        Assertions.assertFalse(deleteApplyCourseDetail.isPresent());

    }
}
