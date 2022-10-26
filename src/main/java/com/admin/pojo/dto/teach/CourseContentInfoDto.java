package com.admin.pojo.dto.teach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseContentInfoDto implements Serializable {

    private static final long serialVersionUID = -2554569604580427409L;

    private String id;

    private String op;
}