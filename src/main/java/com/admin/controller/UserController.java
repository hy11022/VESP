package com.admin.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.admin.pojo.entity.TokenEntity;
import com.admin.pojo.entity.UserEntity;
import com.alibaba.fastjson.JSONObject;
import com.admin.service.TokenService;
import com.alibaba.fastjson.JSONArray;
import com.admin.service.UserService;
import com.admin.pojo.vo.user.UserVo;
import com.admin.util.CommonUtils;
import com.admin.pojo.dto.user.*;
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
                String now = CommonUtils.getTime(0);    //获取当前时间
                loginDto.setLastLoginTime(now);
                userService.updateLastLoginTime(loginDto);
                return Result.showInfo("00000000", "Success", tokenInfo);
            } else {
                return Result.showInfo("00000002", "登录失败", null);
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
            return Result.showInfo("00000001", "入参有误，退出失败", null);
        }
        String accessToken = authorization.getString("accessToken");
        boolean isSuccess = tokenService.deleteToken(accessToken);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000002", "操作数据库出错，退出失败", null);
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
            return Result.showInfo("00000002", "入参格式有误", null);
        }
        JSONArray ja = new JSONArray();
        int totalCount;
        if(userFilterDto.getAuthLevel().equals("")){
            userFilterDto.setAuthLevel("1");
            List<UserVo> userList1 = userService.getUserList(userFilterDto);
            ja.addAll(userList1);
            int count1 = userService.getUserListTotalCount(userFilterDto);
            userFilterDto.setAuthLevel("2");
            List<UserVo> userList2 = userService.getUserList(userFilterDto);
            ja.addAll(userList2);
            int count2 = userService.getUserListTotalCount(userFilterDto);
            userFilterDto.setAuthLevel("3");
            List<UserVo> userList3 = userService.getUserList(userFilterDto);
            ja.addAll(userList3);
            int count3 = userService.getUserListTotalCount(userFilterDto);
            userFilterDto.setAuthLevel("4");
            List<UserVo> userList4 = userService.getUserList(userFilterDto);
            ja.addAll(userList4);
            int count4 = userService.getUserListTotalCount(userFilterDto);
            userFilterDto.setAuthLevel("5");
            List<UserVo> userList5 = userService.getUserList(userFilterDto);
            ja.addAll(userList5);
            int count5 = userService.getUserListTotalCount(userFilterDto);
            totalCount = count1+count2+count3+count4+count5;
        }else{
            List<UserVo> userList = userService.getUserList(userFilterDto);
            ja.addAll(userList);
            totalCount = userService.getUserListTotalCount(userFilterDto);
        }
        return Result.showList("00000000", "Success", ja, totalCount);
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
            return Result.showInfo("00000002", "失败", null);
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(userInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }

    //新增用户
    @PostMapping("/addUser")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addUser(@Validated @RequestBody AddUserDto addUserDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<UserEntity> userList = userService.checkUserByDto(addUserDto);
        if (userList.size() > 0) {
            return Result.showInfo("00000002", "用户已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间auth_levelflag
        addUserDto.setUpdateTime(now);
        addUserDto.setStatus("1");
        boolean isSuccess = userService.addUser(addUserDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除用户
    @PostMapping("/deleteUser")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteUser(@Validated @RequestBody DeleteUserDto deleteUserDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int userID = deleteUserDto.getId();
        List<UserEntity> classesList = userService.getUserByID(userID);
        if (classesList.size() < 1) {
            return Result.showInfo("00000002", "查无此用户", null);
        }
        deleteUserDto.setStatus("3");
        String now = CommonUtils.getTime(0);    //获取当前时间
        deleteUserDto.setUpdateTime(now);
        boolean isSuccess = userService.deleteUser(deleteUserDto);
        if(isSuccess){
            return Result.showInfo("00000000", "Success", null);
        }else{
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //更新用户
    @PostMapping("/updateUser")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateUser(@Validated @RequestBody UpdateUserDto updateUserDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int userID = updateUserDto.getId();
        List<UserEntity> userList = userService.getUserByID(userID);
        if (userList.size() < 1) {
            return Result.showInfo("00000002", "查无此用户", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateUserDto.setUpdateTime(now);
        boolean isSuccess = userService.updateUser(updateUserDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更新用户密码
    @PostMapping("/updateUserPassword")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateUserPassword(@Validated @RequestBody UpdateUserPasswordDto updateUserPasswordDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int userID = updateUserPasswordDto.getId();
        List<UserEntity> userList = userService.getUserByID(userID);
        if (userList.size() < 1) {
            return Result.showInfo("00000002", "查无此用户", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateUserPasswordDto.setUpdateTime(now);
        boolean isSuccess = userService.updateUserPassword(updateUserPasswordDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "修改失败", null);
        }
    }

    //更新用户状态
    @PostMapping("/updateUserStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateUserStatus(@Validated @RequestBody UpdateUserStatusDto updateUserStatusDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int userID = updateUserStatusDto.getId();
        List<UserEntity> userList = userService.getUserByID(userID);
        if (userList.size() < 1) {
            return Result.showInfo("00000002", "查无此用户", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateUserStatusDto.setUpdateTime(now);
        boolean isSuccess = userService.updateUserStatus(updateUserStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "修改失败", null);
        }
    }
}