package com.admin.service;

import com.admin.pojo.dto.user.LoginDto;
import com.admin.pojo.dto.user.TokenInfoDto;
import com.admin.pojo.dto.user.UserFilterDto;
import com.admin.pojo.entity.UserEntity;
import com.admin.pojo.vo.user.UserVo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    List<UserEntity> getUserList(UserFilterDto userFilterDto);

    int getUserListTotalCount(UserFilterDto userFilterDto);

    List<UserEntity> checkUser(LoginDto loginDto);

    List<UserVo> getUserInfoByToken(TokenInfoDto tokenInfoDto);
}