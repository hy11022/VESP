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
public class SpecialityEntity implements Serializable {

    private static final long serialVersionUID = 1605094182012415020L;

    private int id;

    private String code;

    private String name;

    private String departmentID;

    private String updateTime;

    private String status;
}