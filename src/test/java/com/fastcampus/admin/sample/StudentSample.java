package com.fastcampus.admin.sample;

import com.fastcampus.admin.FastcampusStudyadminApplicationTests;
import com.fastcampus.admin.model.entity.Student;
import com.fastcampus.admin.model.enumclass.StudentStatus;
import com.fastcampus.admin.respository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
public class StudentSample extends FastcampusStudyadminApplicationTests {

    private Random random;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void sampleCreate(){

        random = new Random();

        for(int i = 1 ; i < 1001; i++){
            // 가입 상태 랜덤
            int div = (random.nextInt(10)+1) % 2;
            StudentStatus status = (div == 0 ? StudentStatus.REGISTERED : StudentStatus.UNREGISTERED);

            Student user = Student.builder()
                    .account("TestUser"+i)
                    .password("password"+i)
                    .status(status)
                    .email("TestUser"+i+"@gmail.com")
                    .phoneNumber("010-1111-"+String.format("%04d", i))
                    .registeredAt(getRandomDate())
                    .unregisteredAt(status.equals(StudentStatus.UNREGISTERED) ? getRandomDate() : null )
                    .build();

//            log.info("{}",user);
            studentRepository.save(user);
        }


    }


    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2019,getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }
}
