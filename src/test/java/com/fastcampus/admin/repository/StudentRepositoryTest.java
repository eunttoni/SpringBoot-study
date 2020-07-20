package com.fastcampus.admin.repository;

import com.fastcampus.admin.FastcampusStudyadminApplicationTests;
import com.fastcampus.admin.model.entity.Student;
import com.fastcampus.admin.model.enumclass.StudentStatus;
import com.fastcampus.admin.respository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("학생 repository 테스트")
public class StudentRepositoryTest extends FastcampusStudyadminApplicationTests {

    /*
REVIEW
보통 crud 테스트의 경우, 생성부터 한번에 다 하는 경우가 많습니다.
더불어 @Rollback annotation을 사용하여, 테스트용 데이터베이스에 데이터가 많이 쌓이는 것을 방지하기도 합니다.
* */
    @Test
    @Rollback
    public void crud() {

        /*
        REVIEW
        10자리 랜덤 문자열 생성.
        보통 account 같은 경우 unique key로 쓰이는 경우가 많기 때문에 random 문자열을 생성해서 테스트하는 경우가 많습니다.
        * */
        String randomString = new Random().ints('a', 'z' + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        /*
        REVIEW
        password 같은경우는 암호화해서 저장해야하는 필드입니다.
        해당 과제의 범위를 넘어서지만 실무에서 password가 암호화 되지 않는 경우가 없어 코맨트 드립니다.
        * */
        // create
        Student student = new Student();
        student.setAccount("account");
        student.setPassword("abcd");
        student.setStatus(StudentStatus.UNREGISTERED);
        student.setEmail(randomString);
        student.setPhoneNumber("010-1111-2222");
        student.setCreatedAt(LocalDateTime.now());
        student.setCreatedBy("Admin");

        Student saved = studentRepository.save(student);
        Assertions.assertNotNull(saved);


        // read
        Student read = studentRepository.findByEmail(saved.getEmail()).orElse(null);
        Assertions.assertNotNull(read);

        // update
        String randomString2 = new Random().ints('a', 'z' + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        read.setEmail(randomString2);
        read.setUpdatedAt(LocalDateTime.now());
        read.setUpdatedBy("update method()");

        studentRepository.save(read);

        // check update
        Student updated = studentRepository.findByEmail(randomString2).orElse(null);
        Assertions.assertNotNull(updated);
        Assertions.assertEquals(updated.getEmail(), randomString2);

        // delete
        studentRepository.delete(updated);
        Assertions.assertNull(studentRepository.findByEmail(randomString2).orElse(null));
    }



    // Dependency Injection (DI)
    @Autowired
    private StudentRepository studentRepository;

    // TODO Student Entity, Repository 테스트 코드를 작성 하세요
    @Test
    @Transactional
    public void create(){
        String account = "Test03";
        String password = "Test03";
        StudentStatus status = StudentStatus.REGISTERED;
        String email = "Test03@gmail.com";
        String phoneNumber = "010-3333-3333";
        LocalDateTime registeredAt = LocalDateTime.now();

        Student student = new Student();
        student.setAccount(account);
        student.setPassword(password);
        student.setStatus(status);
        student.setEmail(email);
        student.setPhoneNumber(phoneNumber);
        student.setRegisteredAt(registeredAt);
        student.setCreatedAt(LocalDateTime.now());
        student.setCreatedBy("admin");

        Student newStudent = studentRepository.save(student);
        Assertions.assertNotNull(newStudent);
    }

    @Test
    @Transactional
    public void read(){
        Optional<Student> student = studentRepository.findById(2L);
//        Optional<Student> student = studentRepository.findByStatus(StudentStatus);
        Assertions.assertTrue(student.isPresent());

        student.ifPresent(i ->{
            System.out.println(i);
        });

    }

    @Test
    @Transactional
    public void update(){
        Optional<Student> student = studentRepository.findById(2L);

        student.ifPresent(selectStudent ->{
            selectStudent.setAccount("PPPP");
            selectStudent.setUpdatedAt(LocalDateTime.now());
            selectStudent.setUpdatedBy("update method()");

            studentRepository.save(selectStudent);
        });

    }

    /*
    REVIEW
    delete, update test의 경우, 생성도 함께 해주어야 자동적으로 테스트 되는 코드를 만들 수 있습니다.
    * */
    @Test
    @Transactional
    public void delete(){
        Optional<Student> student = studentRepository.findById(2L);

        Assertions.assertTrue(student.isPresent());

        student.ifPresent(selectStudent ->{
            studentRepository.delete(selectStudent);
        });

        Optional<Student> deleteStudent = studentRepository.findById(2L);
        Assertions.assertFalse(deleteStudent.isPresent());

//        if(deleteStudent.isPresent()){
//            System.out.println("deleteStudent 존재 : " + deleteStudent);
//        }else{
//            System.out.println("deleteStudent 없음 : " + deleteStudent);      // Optional.empty
//        }

    }

}
