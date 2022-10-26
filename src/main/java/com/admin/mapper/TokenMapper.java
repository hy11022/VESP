package com.admin.mapper;

import com.admin.pojo.dto.user.TokenDto;
import com.admin.pojo.entity.TokenEntity;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TokenMapper {

    @Select("SELECT * FROM tokens WHERE account = #{account}")
    List<TokenEntity> getTokenList(String account);

    @Insert("INSERT INTO tokens (account,access_token,access_token_expire_time,refresh_token,refresh_token_expire_time) " +
            "VALUES (#{account},#{accessToken},#{accessTokenExpireTime},#{refreshToken},#{refreshTokenExpireTime})")
    boolean addToken(TokenDto tokenDto);

    @Delete("DELETE FROM tokens WHERE access_token = #{accessToken}")
    void deleteToken(String accessToken);

    @Update("UPDATE tokens SET access_token_expire_time = #{accessTokenExpireTime},access_token = #{accessToken}," +
            "refresh_token = #{refreshToken},refresh_token_expire_time = #{refreshTokenExpireTime} WHERE account = #{account}")
    boolean updateToken(TokenDto tokenDto);
}