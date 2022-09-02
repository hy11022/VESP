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
public class UserEntity implements Serializable {

    private int id;

    private String account;

    private String name;

    private String password;

    private String role;

    private String belongType;

    private String belongID;

    private String isAdmin;

    private String status;
}