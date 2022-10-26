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
public class TermEntity implements Serializable {

    private static final long serialVersionUID = -4278720141773867803L;

    private int id;

    private String name;

    private String createTime;

    private String updateTime;

    private String status;
}