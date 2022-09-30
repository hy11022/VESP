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
public class CourseEntity implements Serializable {

    private static final long serialVersionUID = 5254741858852070095L;

    private int id;

    private String name;

    private int departmentID;

    private String labelIDs;

    private String introduction;

    private String cover;

    private String updateTime;

    private String status;
}