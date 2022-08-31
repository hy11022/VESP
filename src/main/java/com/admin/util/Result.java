package com.admin.util;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

/**
 * @BelongsProject: STMS-End
 * @BelongsPackage: com.admin.pojo.vo
 * @Author: Du Rongjun
 * @CreateTime: 2022-07-12  15:18
 * @Description:
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class Result {
  private String resCode;
  private String resMsg;
  private JSONObject resInfo;
  private List resList;
  private Integer totalCount;

  public static Result showInfo(String resCode, String resMsg, JSONObject resInfo){
    return new Result(resCode,resMsg,resInfo,null,null);
  }

  public static Result showList(String resCode, String resMsg, List resList, Integer totalCount){
    return new Result(resCode,resMsg,null,resList,totalCount);
  }
}
