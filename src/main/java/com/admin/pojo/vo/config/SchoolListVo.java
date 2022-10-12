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
public class SchoolListVo implements Serializable {

    private static final long serialVersionUID = 2534826380338982447L;

    private int id;

    private String name;

    private String code;

    private String createTime;

    private String updateTime;

    private String status;
}