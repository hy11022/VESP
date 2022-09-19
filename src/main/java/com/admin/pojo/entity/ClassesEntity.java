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
public class ClassesEntity implements Serializable {

    private static final long serialVersionUID = -4642597429318640127L;

    private int id;

    private String name;

    private int departmentID;

    private String updateTime;

    private String status;
}