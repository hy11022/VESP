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
public class TaskEntity implements Serializable {

    private static final long serialVersionUID = -1481049102383923771L;

    private int id;

    private String termID;

    private String type;

    private String contentID;

    private String startTime;

    private String endTime;

    private String createTime;

    private String createrAccount;

    private String status;
}