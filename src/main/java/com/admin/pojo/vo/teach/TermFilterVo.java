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
public class TermFilterVo implements Serializable {

    private static final long serialVersionUID = -3425145677659808154L;

    private int id;

    private String name;

    private String createTime;

    private String updateTime;

    private String status;
}