package com.admin.pojo.vo.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskChildFilterVo implements Serializable {

    private static final long serialVersionUID = -7698250755087527051L;

    private int id;

    private String taskID;

    private String makerAccount;

    private String makerName;

    private String submitTime;

    private String score;

    private String createTime;

    private String status;
}