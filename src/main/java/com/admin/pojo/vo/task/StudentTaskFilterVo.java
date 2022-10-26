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
public class StudentTaskFilterVo implements Serializable {

    private static final long serialVersionUID = 1554651701004542173L;

    private int id;

    private String termID;

    private String type;

    private String contentID;

    private String contentName;

    private String startTime;

    private String endTime;

    private String status;
}