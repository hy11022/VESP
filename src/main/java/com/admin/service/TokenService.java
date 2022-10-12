package com.admin.service;

import com.admin.pojo.dto.user.TokenDto;
import com.admin.pojo.entity.TokenEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TokenService {

    List<TokenEntity> getTokenList(String account);

    Boolean addToken(TokenDto tokenDto);

    void deleteToken(String accessToken);

    Boolean updateToken(TokenDto tokenDto);
}