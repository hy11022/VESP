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
public class AddQuestionSelectDto implements Serializable {

    private static final long serialVersionUID = -5400650779542178728L;

    private String content;

    private int questionID;

    private String isCorrect;
}