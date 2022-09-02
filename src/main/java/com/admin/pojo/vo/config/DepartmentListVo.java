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
public class DepartmentListVo implements Serializable {

    private int id;

    private String name;

    private String schoolID;

    private String updateTime;

    private String status;
}