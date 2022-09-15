package com.admin.mapper;

import com.admin.pojo.dto.user.*;
import com.admin.pojo.entity.UserEntity;
import com.admin.pojo.vo.user.UserVo;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {

    List<UserVo> getUserList(UserFilterDto userFilterDto);

    @Select("SELECT * FROM users WHERE account = #{account} AND password = #{password} AND status = #{status}")
    List<UserEntity> checkUser(LoginDto loginDto);

    @Select("SELECT a.* FROM users a LEFT JOIN tokens b ON a.account = b.account WHERE b.access_token = #{token}")
    List<UserVo> getUserInfoByToken(TokenInfoDto tokenInfoDto);

    @Update("UPDATE users SET last_login_time = #{lastLoginTime} WHERE account=#{account}")
    void updateLastLoginTime(LoginDto loginDto);

    @Select("SELECT * FROM users WHERE account=#{account} AND auth_level=#{authLevel} AND belong_id=#{belongID}")
    List<UserEntity> checkUserByDto(AddUserDto addUserDto);

    @Insert("INSERT INTO users SET account=#{account},name =#{name},password=#{password},role = #{role}," +
            "auth_level=#{authLevel},belong_id=#{belongID},update_time=#{updateTime},status=#{status}")
    boolean addUser(AddUserDto addUserDto);

    @Select("SELECT * FROM users WHERE id = #{id}")
    List<UserEntity> getUserByID(int id);

    @Delete("DELETE FROM users WHERE id = #{id}")
    boolean deleteUser(DeleteUserDto deleteUserDto);

    @Update("UPDATE users SET name =#{name},role=#{role},auth_level=#{authLevel}," +
            "belong_id = #{belongID},update_time=#{updateTime} WHERE id =#{id}")
    boolean updateUser(UpdateUserDto updateUserDto);

    @Update("UPDATE users SET password =#{password},update_time=#{updateTime} WHERE id =#{id}")
    boolean updateUserPassword(UpdateUserPasswordDto updateUserPasswordDto);

    @Update("UPDATE users SET status =#{status},update_time=#{updateTime} WHERE id =#{id}")
    boolean updateUserStatus(UpdateUserStatusDto updateUserStatusDto);
}