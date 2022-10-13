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
public class LabelListVo implements Serializable {

    private static final long serialVersionUID = -1562650791223671480L;

    private int id;

    private String name;

    private String type;

    private String effect;

    private String createTime;

    private String updateTime;

    private String status;
}
