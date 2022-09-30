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
public class ExperimentEntity implements Serializable {

    private static final long serialVersionUID = 5149457452716792104L;

    private int id;

    private String name;

    private int labelID;

    private String introduction;

    private String author;

    private int departmentID;

    private String introVideoPath;

    private String teachVideoPath;

    private int readCount;

    private String lastPublishtime;

    private String lastPublisherAccount;

    private String updateTime;

    private String status;
}