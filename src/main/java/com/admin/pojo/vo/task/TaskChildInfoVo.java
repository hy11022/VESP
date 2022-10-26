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
public class TaskChildInfoVo implements Serializable {

    private static final long serialVersionUID = 8558657795547142612L;

    private int id;

    private String taskID;

    private String contentID;

    private String makerAccount;

    private String submitTime;

    private String startTime;

    private String endTime;

    private String score;

    private String createTime;

    private String status;
}