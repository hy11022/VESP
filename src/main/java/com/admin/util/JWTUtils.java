package com.admin.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: VESP
 * @BelongsPackage: com.admin.util
 * @Author: Du Rongjun
 * @CreateTime: 2022-06-22  09:29
 * @Description: JWT
 * @Version: 1.0
 */
public class JWTUtils {
    //token秘钥,公司首字母缩写+日期+DRJ
    private static final String TOKEN_SECRET = "HWZNKJYXZRGS20220622DRJ";
    /**
     * @description:生成tokenInfo
     * @author: Du Rongjun
     * @date: 2022-07-07  09:29
     * @param: @param account,accountName,expireTime 账户ID,账户名称,有效时间
     * @return: 加密的token
     **/
    public static String createToken(String account, String name, Date expireTime) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return JWT.create().withHeader(header)
                .withClaim("account", account)
                .withClaim("name", name)
                .sign(algorithm);
    }

    /**
     * 生成tokenInfo
     * @param account,user,accExpTime,refExpTime 账户ID,用户,accessToken有效时间，refreshToken有效时间
     * @return tokenInfo
     */
    public static JSONObject createTokenInfo(String account, String name, Integer accExpTime, Integer refExpTime) {
        JSONObject tokenInfo = new JSONObject();
        Calendar ae = Calendar.getInstance();
        Calendar re = Calendar.getInstance();
        ae.add(Calendar.SECOND, accExpTime);
        re.add(Calendar.SECOND, refExpTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tokenInfo.put("accessToken", createToken(account, name, ae.getTime()));
        tokenInfo.put("refreshToken", createToken(account, name, re.getTime()));
        tokenInfo.put("accessTokenExpireTime", dateFormat.format(ae.getTime()));
        tokenInfo.put("refreshTokenExpireTime", dateFormat.format(re.getTime()));
        return tokenInfo;
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("name").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取登陆用户ID
     * @param token 登录验证
     * @return 返回值
     */
    public static String getAccount(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("account").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
