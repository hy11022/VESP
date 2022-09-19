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
public class ProvinceEntity implements Serializable {

    private static final long serialVersionUID = 978849462629910512L;

    private int id;

    private String name;

    private String code;

    private String updateTime;

    private String status;
}