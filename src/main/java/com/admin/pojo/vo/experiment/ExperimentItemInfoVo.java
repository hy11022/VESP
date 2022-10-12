package com.admin.pojo.vo.experiment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentItemInfoVo implements Serializable {

    private static final long serialVersionUID = 9069395922424296397L;

    private int id;

    private int experimentID;

    private String experimentName;

    private String name;

    private String coverImg;

    private String isTeach;

    private String teachContent;

    private String teachVideo;

    private String isTrain;

    private String trainPath;

    private String isCheck;

    private String checkPath;

    private String createTime;

    private String updateTime;

    private String status;
}