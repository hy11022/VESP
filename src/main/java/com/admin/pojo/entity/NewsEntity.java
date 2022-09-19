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
public class NewsEntity implements Serializable {

    private static final long serialVersionUID = -150245600932809224L;

    private int id;

    private String name;

    private String typeID;

    private String introduction;

    private String cover;

    private String content;

    private String author;

    private String lastPublishtime;

    private String lastPublisherAccount;

    private String lastPublisherName;

    private String readCount;

    private String updateTime;

    private String status;
}