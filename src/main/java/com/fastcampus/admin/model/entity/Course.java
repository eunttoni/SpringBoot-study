package com.fastcampus.admin.model.entity;

import com.fastcampus.admin.model.enumclass.CourseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Getter
@Setter
@ToString(exclude = {"courseDetailList"})
public class Course extends BaseEntity{


    private String title;                           // 강좌 타이틀

    @Enumerated(EnumType.STRING)
    private CourseStatus status;                    // 강좌 상태

    private String teacherName;                     // 강사 이름

    private String teacherPhoneNumber;              // 강사 전화번호

    private String teacherEmail;                    // 강사 이메일

    private BigDecimal amount;                      // 강좌 금액

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<CourseDetail> courseDetailList;    // 강좌 상세 리스트

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<ApplyCourse> applyCourseList;      // 수강신청 강좌 리스트


}
