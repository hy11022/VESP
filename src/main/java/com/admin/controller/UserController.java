package com.admin.controller;

import com.admin.pojo.dto.user.TokenInfoDto;
import com.admin.pojo.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.admin.pojo.dto.user.UserFilterDto;
import com.admin.pojo.entity.TokenEntity;
import com.admin.pojo.dto.user.LoginDto;
import com.admin.pojo.dto.user.TokenDto;
import com.alibaba.fastjson.JSONObject;
import com.admin.pojo.entity.UserEntity;
import com.admin.service.TokenService;
import com.admin.service.UserService;
import com.admin.util.JWTUtils;
import com.admin.util.Result;
import java.util.Objects;
import java.util.List;
import java.util.Map;

//@RestController是@Controller和@ResponseBody的结合体。@Controller类中的方法可以直接通过返回String跳转到jsp、ftl、html等模版页面。
// 在方法上加@ResponseBody注解，也可以返回实体对象。@RestController类中的所有方法只能返回String、Object、Json等实体对象，不能跳转到模版页面。
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    //登录
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        boolean isSuccess;
        loginDto.setStatus("1");
        String account = loginDto.getAccount();
        List<UserEntity> userList = userService.checkUser(loginDto);//判断用户是否可登录
        if (userList.size() > 0) {//用户可登录
            List<TokenEntity> tokenList = tokenService.getTokenList(account);
            UserEntity user = userList.get(0);
            user.setPassword(null);
            JSONObject tokenInfo = JWTUtils.createTokenInfo(account, user.getName(), 6 * 60 * 60, 24 * 60 * 60);
            TokenDto tokenDto = new TokenDto();
            tokenDto.setAccount(account);
            tokenDto.setRefreshToken(tokenInfo.getString("refreshToken"));
            tokenDto.setAccessToken(tokenInfo.getString("accessToken"));
            tokenDto.setRefreshTokenExpireTime(tokenInfo.getString("refreshTokenExpireTime"));
            tokenDto.setAccessTokenExpireTime(tokenInfo.getString("accessTokenExpireTime"));
            if (tokenList.size() > 0) {//token存在，用户已经登录，更新token
                isSuccess = tokenService.updateToken(tokenDto);
            } else {//token不存在，新增token，登录
                isSuccess = tokenService.addToken(tokenDto);
            }
            if (isSuccess) {
                return Result.showInfo("00000000", "Success", tokenInfo);
            } else {
                return Result.showInfo("00000004", "登录失败", null);
            }
        } else {
            return Result.showInfo("00000003", "账户名或密码不正确", null);
        }
    }

    //退出登录
    @PostMapping("/logout")
    public Result logout(@Validated @RequestHeader Map headers) {
        JSONObject authorization;
        try {
            authorization = JSONObject.parseObject(headers.get("authorization").toString());
        } catch (Exception e) {
            return Result.showInfo("00000002", "入参有误，退出失败", null);
        }
        String accessToken = authorization.getString("accessToken");
        boolean isSuccess = tokenService.deleteToken(accessToken);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000001", "操作数据库出错，退出失败", null);
        }
    }

    //查询用户列表
    @PostMapping("/getUserList")
    public Result getUserList(@Validated @RequestBody UserFilterDto userFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        //校验入参格式，账户ID不能超过20位，账户名称不能超过20位
        if (userFilterDto.getAccount().length() > 20 || userFilterDto.getName().length() > 20) {
            return Result.showInfo("00000001", "入参格式有误", null);
        }
        List<UserEntity> userList = userService.getUserList(userFilterDto);
        int totalCount = userService.getUserListTotalCount(userFilterDto);
        return Result.showList("00000000", "Success", userList, totalCount);
    }

    //根据token获取用户信息
    @PostMapping("/getUserInfoByToken")
    public Result getUserInfoByToken(@Validated @RequestBody TokenInfoDto tokenInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<UserVo> userInfo = userService.getUserInfoByToken(tokenInfoDto);
        if(userInfo.size()<1){
            return Result.showInfo("00000000", "失败", null);
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(userInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }
}