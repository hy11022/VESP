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
public class ClasseesEntity implements Serializable {

    private static final long serialVersionUID = 6078964018848684407L;

    private int id;

    private String code;

    private String name;

    private String specialityID;

    private String beginYear;

    private String updateTime;

    private String status;
}