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
public class DepartmentEntity implements Serializable {

    private static final long serialVersionUID = -3103083150793019174L;

    private int id;

    private String name;

    private String code;

    private int schoolID;

    private String updateTime;

    private String status;
}