package com.admin.interceptor;

import com.admin.pojo.dto.user.TokenDto;
import com.admin.service.TokenService;
import com.admin.util.CommonUtils;
import com.admin.util.JWTUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            response.setStatus(401);
            return false;
        }
        JSONObject tokenInfo = JSONObject.parseObject(authorization);
        boolean result = JWTUtils.verify(tokenInfo.get("accessToken").toString());
        //校验token正确，刷新时间没过期
        if (result && !CommonUtils.isBeforeNow(tokenInfo.get("refreshTokenExpireTime").toString())) {
            //accessToken过期，更新token
            if (CommonUtils.isBeforeNow(tokenInfo.get("accessTokenExpireTime").toString())) {
                String account = JWTUtils.getAccount(tokenInfo.get("accessToken").toString());
                String name = JWTUtils.getName(tokenInfo.get("accessToken").toString());
                JSONObject tokenInfoNew = JWTUtils.createTokenInfo(account, name, 6 * 60 * 60, 24 * 60 * 60);
                TokenDto tokenDto = new TokenDto();
                tokenDto.setAccount(account);
                tokenDto.setAccessToken(tokenInfoNew.getString("accessToken"));
                tokenDto.setAccessTokenExpireTime(tokenInfoNew.getString("accessTokenExpireTime"));
                tokenDto.setRefreshToken(tokenInfoNew.getString("refreshToken"));
                tokenDto.setRefreshTokenExpireTime(tokenInfoNew.getString("refreshTokenExpireTime"));
                tokenService.updateToken(tokenDto);
            }
            return true;
        } else {
            response.setStatus(401);
            return false;
        }
    }
}