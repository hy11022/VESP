package com.admin.pojo.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswersRecordsDto implements Serializable {

    private static final long serialVersionUID = -3763729212787832277L;

    private int id;

    private int taskChildID;

    private String questionCopyID;

    private String answer;

    private String score;

    private String status;
}