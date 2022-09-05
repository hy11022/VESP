package com.admin.pojo.vo.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionModuleListVo implements Serializable {

    private int id;

    private String name;

    private String remark;

    private String coverImg;

    private String path;

    private String status;
}