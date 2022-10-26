package com.admin.service;

import com.admin.pojo.dto.task.UserForTaskDto;
import com.admin.pojo.dto.user.*;
import com.admin.pojo.entity.UserEntity;
import com.admin.pojo.vo.user.UserVo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    List<UserVo> getUserList(UserFilterDto userFilterDto);

    List<UserVo> getUserByToken(UserTokenDto userTokenDto);

    int getUserListTotalCount(UserFilterDto userFilterDto);

    List<UserEntity> checkUser(LoginDto loginDto);

    List<UserVo> getUserInfoByToken(TokenInfoDto tokenInfoDto);

    List<UserEntity> checkUserInSchool(AddUserDto addUserDto);

    List<UserEntity> getUserByID(int id);

    List<UserEntity> getUserForTask(UserForTaskDto userForTaskDto);

    void updateLastLoginTime(LoginDto loginDto);

    boolean addUser(AddUserDto addUserDto);

    boolean deleteUser(DeleteUserDto deleteUserDto);

    boolean updateUser(UpdateUserDto updateUserDto);

    boolean updateUserPassword(UpdateUserPasswordDto updateUserPasswordDto);

    boolean updateUserStatus(UpdateUserStatusDto updateUserStatusDto);
}