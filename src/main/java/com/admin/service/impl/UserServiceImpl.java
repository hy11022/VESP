package com.admin.service.impl;

import com.admin.mapper.UserMapper;
import com.admin.pojo.dto.user.*;
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
    public List<UserVo> getUserList(UserFilterDto userFilterDto) {
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

    @Override
    public List<UserEntity> checkUserByDto(AddUserDto addUserDto) {
        return userMapper.checkUserByDto(addUserDto);
    }

    @Override
    public List<UserEntity> getUserByID(int id) {
        return userMapper.getUserByID(id);
    }

    @Override
    public void updateLastLoginTime(LoginDto loginDto) {
        userMapper.updateLastLoginTime(loginDto);
    }

    @Override
    public boolean addUser(AddUserDto addUserDto) {
        return userMapper.addUser(addUserDto);
    }

    @Override
    public boolean deleteUser(DeleteUserDto deleteUserDto) {
        return userMapper.deleteUser(deleteUserDto);
    }

    @Override
    public boolean updateUser(UpdateUserDto updateUserDto) {
        return userMapper.updateUser(updateUserDto);
    }

    @Override
    public boolean updateUserPassword(UpdateUserPasswordDto updateUserPasswordDto) {
        return userMapper.updateUserPassword(updateUserPasswordDto);
    }

    @Override
    public boolean updateUserStatus(UpdateUserStatusDto updateUserStatusDto) {
        return userMapper.updateUserStatus(updateUserStatusDto);
    }
}