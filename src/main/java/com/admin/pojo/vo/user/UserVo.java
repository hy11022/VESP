package com.admin.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {

    private int id;

    private String account;

    private String name;

    private String role;

    private String belongType;

    private String belongID;

    private String isAdmin;

}