package com.admin.pojo.vo.experiment;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperimentItemFilterVo implements Serializable {

    private static final long serialVersionUID = -4766975864800147556L;

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

    private String updateTime;

    private String status;
}