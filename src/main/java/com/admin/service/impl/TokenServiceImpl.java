package com.admin.service.impl;

import com.admin.mapper.TokenMapper;
import com.admin.pojo.dto.user.TokenDto;
import com.admin.pojo.entity.TokenEntity;
import com.admin.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: SPCA-end
 * @BelongsPackage: com.admin.service.impl
 * @Author: Du Rongjun
 * @CreateTime: 2022-07-06  18:07
 * @Description: token
 * @Version: 1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenMapper tokenMapper;

    @Override

    public List<TokenEntity> getTokenList(String account) {
        return tokenMapper.getTokenList(account);
    }

    public Boolean addToken(TokenDto tokenDto) {
        return tokenMapper.addToken(tokenDto);
    }

    public Boolean deleteToken(String accessToken) {
        return tokenMapper.deleteToken(accessToken);
    }

    public Boolean updateToken(TokenDto tokenDto) {
        return tokenMapper.updateToken(tokenDto);
    }
}
