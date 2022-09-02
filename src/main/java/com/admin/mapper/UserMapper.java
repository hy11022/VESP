package com.admin.mapper;

import com.admin.pojo.dto.user.LoginDto;
import com.admin.pojo.dto.user.TokenInfoDto;
import com.admin.pojo.dto.user.UserFilterDto;
import com.admin.pojo.entity.UserEntity;
import com.admin.pojo.vo.user.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {
    List<UserEntity> getUserList(UserFilterDto userFilterDto);
    @Select("SELECT * FROM users WHERE account = #{account} AND password = #{password} AND status = #{status}")
    List<UserEntity> checkUser(LoginDto loginDto);

    @Select("SELECT a.* FROM users a LEFT JOIN tokens b ON a.account = b.account WHERE b.access_token = #{token}")
    List<UserVo> getUserInfoByToken(TokenInfoDto tokenInfoDto);
}