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
public class NewsVo implements Serializable {

    private static final long serialVersionUID = -1785847256520963686L;

    private int id;

    private int typeID;

    private String typeName;

    private String name;

    private String introduction;

    private String cover;

    private String author;

    private String lastPublishTime;

    private String lastPublisherName;

    private String lastPublisherAccount;

    private String readCount;

    private String createTime;

    private String updateTime;

    private String status;
}
