package com.admin.service;

import com.admin.pojo.dto.user.*;
import com.admin.pojo.entity.SchoolEntity;
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

    List<SchoolEntity> getSchoolByClassID(int classID);

    List<SchoolEntity> getSchoolBySpecialityID(int specialityID);

    List<SchoolEntity> getSchoolByDepartmentID(int departmentID);

    void updateLastLoginTime(LoginDto loginDto);

    boolean addUser(AddUserDto addUserDto);

    boolean deleteUser(DeleteUserDto deleteUserDto);

    boolean updateUser(UpdateUserDto updateUserDto);

    boolean updateUserPassword(UpdateUserPasswordDto updateUserPasswordDto);

    boolean updateUserStatus(UpdateUserStatusDto updateUserStatusDto);
}