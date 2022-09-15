package com.admin.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassListVo implements Serializable {

    private static final long serialVersionUID = 9037967612202737129L;

    private int id;

    private String name;

    private String code;

    private String beginYear;

    private String schoolID;

    private String schoolName;

    private String departmentID;

    private String departmentName;

    private String specialityID;

    private String specialityName;

    private String updateTime;

    private String status;
}