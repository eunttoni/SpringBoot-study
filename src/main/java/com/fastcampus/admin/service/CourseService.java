package com.fastcampus.admin.service;

import com.fastcampus.admin.model.entity.Course;
import com.fastcampus.admin.model.enumclass.CourseStatus;
import com.fastcampus.admin.model.http.course.CourseRequest;
import com.fastcampus.admin.respository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course create(CourseRequest courseRequest){
        Course course = Course.builder()
                .title(courseRequest.getTitle())
                .status(CourseStatus.OPEN)               // TODO ENUM Class로 변환해주세요
                .teacherName(courseRequest.getTeacherName())
                .teacherEmail(courseRequest.getTeacherEmail())
                .teacherPhoneNumber(courseRequest.getTeacherPhoneNumber())
                .amount(courseRequest.getAmount())
                .build();

        return courseRepository.save(course);
    }

    public Optional<Course> read(Long id){
        return courseRepository.findById(id);
    }

    public Course update(CourseRequest courseRequest){
        return Optional.of(courseRequest.getId())
                .map(courseRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(course -> course.setTitle(courseRequest.getTitle())
                        .setTeacherName(courseRequest.getTeacherName())
                        .setTeacherEmail(courseRequest.getTeacherEmail())
                        .setTeacherPhoneNumber(courseRequest.getTeacherPhoneNumber())
                        .setAmount(courseRequest.getAmount()))
                .map(courseRepository::save)
                .orElseGet(()->null);
    }

    public void delete(Long id){
        courseRepository.findById(id).ifPresent(courseRepository::delete);
    }
}
