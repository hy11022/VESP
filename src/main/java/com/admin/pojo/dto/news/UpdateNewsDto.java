package com.admin.pojo.dto.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNewsDto implements Serializable {

    private static final long serialVersionUID = -8000114343045440764L;

    @NotNull(message = "唯一编号不能为空")
    private int id;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotBlank(message = "新闻类别不能为空")
    private String typeID;

    @NotBlank(message = "新闻简介不能为空")
    private String introduction;

    private String cover;

    @NotBlank(message = "新闻内容不能为空")
    private String content;

    @NotBlank(message = "新闻作者不能为空")
    private String author;

    private String status;

    private String lastPublishtime;

    private String lastPublisherAccount;

    private String updateTime;
}