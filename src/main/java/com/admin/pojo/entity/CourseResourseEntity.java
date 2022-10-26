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
public class CourseResourseEntity implements Serializable {

    private static final long serialVersionUID = -5754362649295268594L;

    private int id;

    private String name;

    private int courseID;

    private String introduction;

    private String cover;

    private String type;

    private String path;

    private String createTime;

    private String updateTime;

    private String status;
}