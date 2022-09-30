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
public class ExperimentFilterVo implements Serializable {

    private static final long serialVersionUID = 5035281295096981589L;

    private int id;

    private String name;

    private String coverImg;

    private String introduction;

    private String author;

    private JSONArray labelList;

    private String labelIDs;

    private int departmentID;

    private String departmentName;

    private String introVideoPath;

    private String teachVideoPath;

    private int readCount;

    private int exUserCount;

    private String lastPublishtime;

    private String lastPublisherAccount;

    private String lastPublisherName;

    private String teamMember;

    private String intro;

    private String mustRead;

    private String updateTime;

    private String status;
}