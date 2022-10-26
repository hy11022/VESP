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
public class UpdateTasksChildDto implements Serializable {

    private static final long serialVersionUID = -1356435080644095275L;

    private int id;

    private int taskID;

    private String makerAccount;

    private String submitTime;

    private String score;

    private String createTime;

    private String status;
}