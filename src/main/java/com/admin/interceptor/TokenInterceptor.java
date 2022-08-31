package com.admin.interceptor;

import com.admin.util.CommonUtils;
import com.admin.util.Config;
import com.admin.util.IpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: VESP
 * @BelongsPackage: com.admin.interceptor
 * @Author: Du Rongjun
 * @CreateTime: 2022-07-07  13:40
 * @Description: token验证
 * @Version: 1.0
 */
@EnableTransactionManagement
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("token");
        String AppKey = request.getHeader("AppKey");
        String ipAddr = IpUtil.getClinetIpByReq(request);
        String ipAddr2 = IpUtil.getRealIP(request);
        System.out.println("ipAddr:"+ipAddr);
        System.out.println("ipAddr2:"+ipAddr2);
        if (token == null || AppKey == null) {
            response.setStatus(401);
            return false;
        }
//        response.addHeader("token",token);
//        response.addHeader("AppKey",AppKey);
        //调用易华录验证接口(attrs)
        String url = Config.userService+"/usercenter-service/lanToken/getInfoByToken";
        //String param = "";
        String result = CommonUtils.sendGet(url,token,AppKey);
        JSONObject ja = JSONObject.parseObject(result);
        String code = ja.get("code").toString();
        boolean re = true;
        if(!code.equals("200")){
            response.setStatus(401);
            re = false;
        }
        return re;
    }
}