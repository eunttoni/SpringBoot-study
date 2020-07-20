package com.fastcampus.admin.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplyCourseStatus {

    PROCEEDING(0,"수강중","강의 수강중"),
    EXPECTED(1,"수강 예정","강의 수강 예정"),
    COMPLETE(2,"수강 완료","강의 수강 완료");

    private Integer id;
    private String title;
    private String description;
}
