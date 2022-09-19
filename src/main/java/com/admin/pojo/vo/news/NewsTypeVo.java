package com.admin.pojo.vo.news;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsTypeVo implements Serializable {

    private static final long serialVersionUID = 8618097509418505537L;

    private int id;

    private String name;

    private String updateTime;

    private String status;
}
