package com.yixihan.yicode.gateway;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.yixihan.yicode.common.enums.user.RoleEnums;
import com.yixihan.yicode.common.pojo.Role;
import com.yixihan.yicode.common.util.ValidationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yixihan
 * @date 2022-10-28-0:35
 */
class CommonTest {
    
    @Test
    void test02 () throws Exception{
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ5aXhpaGFuIiwidXNlck1vYmlsZSI6IjE3NjIzODUwNDI2Iiwic2NvcGUiOlsiYWxsIl0sInVzZXJFbWFpbCI6IjMxMTM3ODg5OTdAcXEuY29tIiwiZXhwIjoxNjY4NTA0NzY4LCJ1c2VyTmFtZSI6InlpeGloYW4iLCJ1c2VySWQiOjEsImF1dGhvcml0aWVzIjpbIlNVUEVSX0FETUlOIiwiVVNFUiIsIkFETUlOIl0sImp0aSI6IjIxZGFjZjMzLTViZGEtNDBlYS1iMzAwLWQ2ZjdlNjZiNWNiYSIsImNsaWVudF9pZCI6InlpY29kZSJ9.UU3kuuUdlqUfoJqUAZoyLBG-cyu1idyaSummto9ay1jyaov87z86UKkXJJWeLJGS829kjnQj0q5syx_4GR4I878gTcbZS3iMf-pF6YMlda7ai8HK2waTVDJS3NFNfN-4GuATZs5Ya5THxW_DZYYAcwMMyX78nE7cEOxAsdXI7V6BACoi6F-DhkWT9DGA3B68mHmwdJ3VHOTKOiepU0yzWAkOnmrCNwnIXtkA-A0IfQGmwJZS9gKjmDzlfsiKJTAb7h-PQPEkQtPmeg7b6TN7c-5MbuD-B_4YuuOIn9GiaTw3O4TTQFEEy7xg0-XegDol56mb-WEjemmXssmk7OwI7g";
        JWT parse = JWTParser.parse (token);
        JWTClaimsSet jwtClaimsSet = parse.getJWTClaimsSet ();
        System.out.println (jwtClaimsSet.getClaim ("userName"));
        System.out.println (jwtClaimsSet.getClaim ("userId"));
        String str = jwtClaimsSet.getClaim ("authorities").toString ();
        String[] authorities = str.substring (1, str.length () - 1).split (",");
        List<Role> roleList = new ArrayList<> (authorities.length);
        for (int i = 0; i < authorities.length; i++) {
            authorities[i] = authorities[i].substring (1, authorities[i].length () - 1);
            RoleEnums roleEnums = RoleEnums.valueOf (authorities[i]);
            roleList.add (new Role (roleEnums.getRoleId (), roleEnums.getRoleName ()));
        }
        System.out.println (roleList);
        System.out.println (jwtClaimsSet);
        Assertions.assertTrue (Boolean.TRUE);
    }

    @Test
    void testPassword () {
        String password = "Theyear123，";

        System.out.println (ValidationUtils.validatePassword (password));
        Assertions.assertTrue (Boolean.TRUE);
    }

    @Test
    void testUserName () {
        String userName = "易·曦·翰";

        System.out.println (ValidationUtils.validateUserName (userName));
        Assertions.assertTrue (Boolean.TRUE);
    }
}
