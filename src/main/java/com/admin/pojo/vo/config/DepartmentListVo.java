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
public class DepartmentListVo implements Serializable {

    private static final long serialVersionUID = 5496273581404166755L;

    private int id;

    private String name;

    private String code;

    private String schoolID;

    private String schoolName;

    private String createTime;

    private String updateTime;

    private String status;
}