package com.admin.util;

import com.admin.pojo.dto.user.LoginFormDto;

public class MockUtils {

    /**
     * @description: 用户登录mock
     * @author: Du Rongjun
     * @date:
     * @param: time
     * @return: java.lang.Boolean
     **/
    public static Boolean login(LoginFormDto loginFormDto,String Appkey) {
        return true;
    }

    /**
     * @description: 退出登录mock
     * @author: Du Rongjun
     * @date:
     * @param: time
     * @return: java.lang.Boolean
     **/
    public static Boolean logOut(String token) {
        return true;
    }

    /**
     * @description: 验证tokenmock
     * @author: Du Rongjun
     * @date:
     * @param: GET http://{ip}:8087/userservice/usercenter-service/lanToken/getInfoByToken
     * @return: java.lang.Boolean
     **/
    public static Boolean validToken(String token,String validAppKey) {
        return true;
    }
}
