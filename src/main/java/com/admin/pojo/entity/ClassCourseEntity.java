package com.admin.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassCourseEntity implements Serializable {

    private static final long serialVersionUID = 8047142684889507686L;

    private int id;

    private int classID;

    private int termID;

    private int courseID;

    private String createTime;

    private String updateTime;

    private String status;
}