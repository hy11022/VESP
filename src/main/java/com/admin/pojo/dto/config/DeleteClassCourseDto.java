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
public class DeleteClassCourseDto implements Serializable {

    private static final long serialVersionUID = 3570432181173885791L;

    @NotNull(message = "班级课程唯一编号不能为空")
    private int id;
}