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
public class TaskFilterVo implements Serializable {

    private static final long serialVersionUID = 3265069019605853332L;

    private int id;

    private String termID;

    private String type;

    private String contentID;

    private String contentName;

    private String contentRemark;

    private String contentDuration;

    private String submitCount;

    private String noSubmitCount;

    private String startTime;

    private String endTime;

    private String createTime;

    private String createrAccount;

    private String createrName;

    private String status;
}