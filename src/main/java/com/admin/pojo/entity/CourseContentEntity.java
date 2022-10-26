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
public class CourseContentEntity implements Serializable {

    private static final long serialVersionUID = 9101815138256048133L;

    private int id;

    private String title;

    private String type;

    private String courseID;

    private String questionIDs;

    private String isAllObject;

    private String duration;

    private String scoreArray;

    private String remark;

    private String releaseMethod;

    private String releaseTime;

    private String createMethod;

    private String createTime;

    private String createrAccount;

    private String updateTime;

    private String status;
}