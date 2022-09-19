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
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1991634085349581566L;

    private int id;

    private String account;

    private String name;

    private String password;

    private String role;

    private String authLevel;

    private String belongID;

    private String belongName;

    private String flag;

    private String lastLoginTime;

    private String updateTime;

    private String status;
}