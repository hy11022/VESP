package com.admin.pojo.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskChildDto implements Serializable {

    private static final long serialVersionUID = -658628994650174935L;

    private int taskID;

    private String makerAccount;

    private String submitTime;

    private String score;

    private String createTime;

    private String status;
}