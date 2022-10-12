package com.admin.pojo.vo.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsInfoVo implements Serializable {

    private static final long serialVersionUID = 4336449192886730718L;

    private int id;

    private String name;

    private String typeID;

    private String typeName;

    private String introduction;

    private String cover;

    private String content;

    private String author;

    private String lastPublishtime;

    private String lastPublisherAccount;

    private String lastPublisherName;

    private String readCount;

    private String createTime;

    private String updateTime;

    private String status;
}