package com.admin.pojo.vo.teach;

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
public class CourseFilterVo implements Serializable {

    private static final long serialVersionUID = 5035281295096981589L;

    private int id;

    private String name;

    private int departmentID;

    private String departmentName;

    private String teacherAccount;

    private String teacherName;

    private JSONArray labelList;

    private String labelIDs;

    private String introduction;

    private String cover;

    private String createTime;

    private String updateTime;

    private String status;
}