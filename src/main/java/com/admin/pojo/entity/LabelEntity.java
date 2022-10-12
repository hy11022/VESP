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
public class LabelEntity implements Serializable {

    private static final long serialVersionUID = 4906718936379213428L;

    private int id;

    private String name;

    private String type;

    private String createTime;

    private String updateTime;

    private String status;
}
