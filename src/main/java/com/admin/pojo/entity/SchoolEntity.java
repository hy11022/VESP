package com.admin.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolEntity implements Serializable {

    private static final long serialVersionUID = -4363028148082663109L;

    private int id;

    private String name;

    private String code;

    private String updateTime;

    private String status;
}