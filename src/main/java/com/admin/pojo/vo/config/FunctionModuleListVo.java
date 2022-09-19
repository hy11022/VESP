package com.admin.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FunctionModuleListVo implements Serializable {

    private static final long serialVersionUID = 145740741008380004L;

    private int id;

    private String name;

    private String remark;

    private String coverImg;

    private String headImg;

    private String pathType;

    private String path;

    private String isNeedLogin;

    private String updateTime;

    private String roleLimits;

    private String status;
}