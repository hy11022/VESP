package com.admin.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassCourseFilterVo implements Serializable {

    private static final long serialVersionUID = 9037967612202737129L;

    private int id;

    private int termID;

    private String termName;

    private String classID;

    private String className;

    private String courseID;

    private String courseName;

    private String teacherAccount;

    private String teacherName;

    private String createTime;

    private String updateTime;

    private String status;
}