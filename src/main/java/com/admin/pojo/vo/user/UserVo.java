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

    private static final long serialVersionUID = -446673132087636121L;

    private String id;

    private String account;

    private String name;

    private String role;

    private String authLevel;

    private String belongID;

    private String belongName;

    private String lastLoginTime;

    private String updateTime;

    private String status;
}