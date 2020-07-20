package com.fastcampus.admin.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  CourseStatus {

    OPEN(0,"오픈","강좌 개설"),
    WAITING(1,"대기","강좌 개설 대기중"),
    END(2,"마감","강좌 마감");

    private Integer id;
    private String title;
    private String description;
}
