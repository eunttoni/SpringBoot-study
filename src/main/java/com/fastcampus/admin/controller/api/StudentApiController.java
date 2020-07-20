package com.fastcampus.admin.controller.api;

import com.fastcampus.admin.model.entity.Student;
import com.fastcampus.admin.model.http.Header;
import com.fastcampus.admin.model.http.student.StudentDetailResponse;
import com.fastcampus.admin.model.http.student.StudentRequest;
import com.fastcampus.admin.model.http.student.StudentResponse;
import com.fastcampus.admin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// TODO CRUD Controller 를 작성하세요
@RestController
@RequestMapping("/v1/student")
public class StudentApiController {

    private final StudentService studentService;

    @Autowired
    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("")
    public Student create(@RequestBody StudentRequest studentRequest){
        return studentService.create(studentRequest);
    }

    @GetMapping("/{id}")
    public Optional<Student> read(@PathVariable Long id){
        return studentService.read(id);
    }

    @PutMapping("")
    public Student update(@RequestBody StudentRequest studentRequest){
        return studentService.update(studentRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    // 수강생 전체, 페이징
    @GetMapping("")
    public Header<List<StudentResponse>> findAll(@PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        return studentService.search(pageable);
    }

    // 2_3) 수강 목록, 진행률
    @GetMapping("/detail/{id}")
    public StudentDetailResponse detail(@PathVariable Long id){
        return studentService.detail(id);
    }
}
