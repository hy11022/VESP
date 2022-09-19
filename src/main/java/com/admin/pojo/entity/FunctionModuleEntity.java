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
public class FunctionModuleEntity implements Serializable {

    private static final long serialVersionUID = -7507796896225495812L;

    private int id;

    private String name;

    private String remark;

    private String coverImg;

    private String headImg;

    private String path;

    private String isNeedLogin;

    private String updateTime;

    private String roleLimits;

    private String status;
}