package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClassCourseStatusDto implements Serializable {

    private static final long serialVersionUID = 6641930565448290936L;

    @NotNull(message = "唯一编号不能为空")
    private int id;

    private String status;

    private String updateTime;
}