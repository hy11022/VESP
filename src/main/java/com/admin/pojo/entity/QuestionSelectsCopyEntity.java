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
public class QuestionSelectsCopyEntity implements Serializable {

    private static final long serialVersionUID = 8282810577738315871L;

    private int id;

    private String content;

    private int questionCopyID;

    private String isCorrect;
}