package com.admin.service.impl;

import com.admin.mapper.UserMapper;
import com.admin.pojo.dto.user.LoginDto;
import com.admin.pojo.dto.user.TokenInfoDto;
import com.admin.pojo.dto.user.UserFilterDto;
import com.admin.pojo.entity.UserEntity;
import com.admin.pojo.vo.user.UserVo;
import com.admin.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserEntity> getUserList(UserFilterDto userFilterDto) {
        PageHelper.startPage(userFilterDto.getPageNum(), userFilterDto.getPageSize());
        return userMapper.getUserList(userFilterDto);
    }

    @Override
    public int getUserListTotalCount(UserFilterDto userFilterDto) {
        return userMapper.getUserList(userFilterDto).size();
    }

    @Override
    public List<UserEntity> checkUser(LoginDto loginDto) {
        return userMapper.checkUser(loginDto);
    }

    @Override
    public List<UserVo> getUserInfoByToken(TokenInfoDto tokenInfoDto) {
        return userMapper.getUserInfoByToken(tokenInfoDto);
    }
}