package com.fastcampus.admin.respository;

import com.fastcampus.admin.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    Student findByStatus(String status);
    /*
    REVIEW
    JpaRepository의 경우 아래와 같은 방식으로 Query Method를 생성 할 수 있습니다
    https://arahansa.github.io/docs_spring/jpa.html#repositories.query-methods
    * */
    Optional<Student> findByEmail(String email);
}
