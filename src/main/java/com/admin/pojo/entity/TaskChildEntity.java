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
public class TaskChildEntity implements Serializable {

    private static final long serialVersionUID = 7361513666158207235L;

    private int id;

    private String taskID;

    private String makerAccount;

    private String submitTime;

    private String score;

    private String createTime;

    private String status;
}