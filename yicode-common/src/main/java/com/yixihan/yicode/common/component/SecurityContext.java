package com.yixihan.yicode.common.component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.yixihan.yicode.common.enums.RoleEnum;
import com.yixihan.yicode.common.pojo.Role;
import com.yixihan.yicode.common.pojo.User;
import lombok.Data;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author yixihan
 * @date 2022/11/14 16:40
 */
@Data
public class SecurityContext {

    /**
     * jwt token
     */
    private String token;

    /**
     * 用户
     */
    private User user;

    public SecurityContext (String token) {
        init (token);
    }

    /**
     * 根据 token 获取当前登录用户对象
     */
    private void init (String token) {
        try {
            JWT parse = JWTParser.parse (token);
            JWTClaimsSet jwtClaimsSet = parse.getJWTClaimsSet ();
            setToken (token);
            setUser (new User ());
            getUser ().setUserId (Long.valueOf (jwtClaimsSet.getClaim ("userId").toString ()));
            getUser ().setUserName (jwtClaimsSet.getClaim ("userName").toString ());
            getUser ().setUserEmail (jwtClaimsSet.getClaim ("userEmail").toString ());
            getUser ().setUserMobile (jwtClaimsSet.getClaim ("userMobile").toString ());
            getUser ().setAuthorities (getAuthorities (jwtClaimsSet.getClaim ("authorities").toString ()));
        } catch (ParseException e) {
            throw new RuntimeException (e);
        }
    }

    /**
     * 获取 token 对应用户的权限列表
     */
    private List<Role> getAuthorities (String str) {
        String[] authorities = str.substring (1, str.length () - 1).split (",");
        List<Role> roleList = new ArrayList<> (authorities.length);
        for (int i = 0; i < authorities.length; i++) {
            authorities[i] = authorities[i].substring (1, authorities[i].length () - 1);
            RoleEnum roleEnum = RoleEnum.valueOf (authorities[i]);
            roleList.add (new Role (roleEnum.getRoleId (), roleEnum.getRoleName ()));
        }
        return roleList;
    }


}
