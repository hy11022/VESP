package com.admin.interceptor;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import com.admin.pojo.dto.user.TokenDto;
import com.alibaba.fastjson.JSONObject;
import com.admin.service.TokenService;
import com.admin.util.CommonUtils;
import com.admin.util.JWTUtils;
import java.time.LocalDateTime;

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
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String TokenRequired = request.getHeader("TokenRequired");
        if (TokenRequired.equals("false")) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            response.setStatus(401);
            return false;
        }
        JSONObject tokenInfo = JSONObject.parseObject(authorization);
        boolean result = JWTUtils.verify(tokenInfo.get("accessToken").toString());
        //校验token正确，刷新时间没过期
        if(result && !CommonUtils.isBeforeNow(tokenInfo.get("refreshTokenExpireTime").toString())){
            //accessToken过期，更新token时间，续签六小时
            if(CommonUtils.isBeforeNow(tokenInfo.get("accessTokenExpireTime").toString())){
                String account = JWTUtils.getAccount(tokenInfo.get("accessToken").toString());
                TokenDto tokenDto = new TokenDto();
                //格式化格式
                String format = "YYYY-MM-dd HH:mm:ss";
                LocalDateTime date = LocalDateTime.now();
                String aeTime = date.plusHours(6).format(DateTimeFormatter.ofPattern(format));
                String reTime = date.plusHours(24).format(DateTimeFormatter.ofPattern(format));
                tokenDto.setAccessTokenExpireTime(aeTime);
                tokenDto.setRefreshToken(tokenInfo.getString("refreshToken"));
                tokenDto.setRefreshTokenExpireTime(reTime);
                tokenDto.setAccount(account);
                tokenDto.setAccessToken(tokenInfo.getString("accessToken"));
                tokenService.updateToken(tokenDto);
            }
            return true;
        }else{
            response.setStatus(401);
            return false;
        }
    }

}