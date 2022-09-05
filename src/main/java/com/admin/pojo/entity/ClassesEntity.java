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
public class ClassesEntity implements Serializable {

    private int id;

    private String name;

    private int departmentID;

    private String updateTime;

    private String status;
}