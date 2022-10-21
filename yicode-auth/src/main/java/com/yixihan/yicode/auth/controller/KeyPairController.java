package com.yixihan.yicode.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * 获取RSA公钥接口
 *
 * @author yixihan
 * @date 2022-10-20-17:01
 */
@RestController
public class KeyPairController {

    @Resource
    private KeyPair keyPair;

    @ApiOperation("获取RSA公钥接口")
    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic ();
        RSAKey key = new RSAKey.Builder (publicKey).build ();
        return new JWKSet (key).toJSONObject ();
    }

}
