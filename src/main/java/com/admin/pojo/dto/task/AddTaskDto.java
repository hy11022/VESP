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
public class AddTaskDto implements Serializable {

    private static final long serialVersionUID = 593698272742362064L;

    private int termID;

    private String type;

    private int contentID;

    private String startTime;

    private String endTime;

    private String createTime;

    private String createrAccount;

    private String status;
}