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
public class ExperimentItemEntity implements Serializable {

    private static final long serialVersionUID = 3955208592676743144L;

    private int id;

    private int experimentID;

    private String name;

    private String coverImg;

    private String isTeach;

    private String teachContent;

    private String teachVideo;

    private String isTrain;

    private String trainPath;

    private String isCheck;

    private String checkPath;

    private String updateTime;

    private String status;
}