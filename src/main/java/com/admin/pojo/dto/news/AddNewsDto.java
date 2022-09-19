package com.admin.pojo.dto.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddNewsDto implements Serializable {

    private static final long serialVersionUID = -1596753800233048581L;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String typeID;

    private String introduction;

    private String cover;

    private String content;

    private String author;

    private String status;

    private String lastPublishtime;

    private String lastPublisherAccount;

    private String updateTime;
}