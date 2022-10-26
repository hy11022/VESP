package com.admin.pojo.vo.teach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSelectsCopyVo implements Serializable {

    private static final long serialVersionUID = -7156961324517476313L;

    private int id;

    private String content;

    private String isCorrect;
}