package com.yixihan.yicode.thirdpart.biz.service.code.impl;

import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.util.Assert;
import com.yixihan.yicode.thirdpart.api.prop.code.CodeProp;
import com.yixihan.yicode.thirdpart.biz.service.code.CodeService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 验证码服务类
 *
 * @author yixihan
 * @date 2022-10-26-11:29
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CodeProp codeProp;

    private static final Random random = new Random ();
    
    private static final char[] RANDOM_Arr = "1234567890".toCharArray ();

    public String getCode(String keyName) {
        // 生成验证码
        String code = getRandomCode ();

        // 存入 redis
        addRedis (keyName, code);
        return code;
    }

    @Override
    public void validate(String keyName, String code) {
        // 校验验证码是否已经过期
        Long expire = stringRedisTemplate.getExpire(keyName);
        Assert.isFalse (expire == null || expire < 0L, BizCodeEnum.CODE_EXPIRED_ERR);
        
        // 校验验证码是否正确
        Assert.isTrue (code.equals (stringRedisTemplate.opsForValue ().get (keyName)), BizCodeEnum.CODE_VALIDATE_ERR);
    }

    /**
     * 获取随机验证码, 并存入 redis 中
     *
     * @return code
     */
    private synchronized String getRandomCode() {
        int len = codeProp.getCodeLen ();
        StringBuilder sb = new StringBuilder (len);
        
        for (int i = 0; i < len; i++) {
            sb.append (RANDOM_Arr[random.nextInt (RANDOM_Arr.length)]);
        }

        return sb.toString ();
    }
    
    @Override
    public void addRedis (String keyName, String code) {
        // 存入 redis
        stringRedisTemplate.opsForValue().set(keyName, code);
        // 设置过期时间
        stringRedisTemplate.expire(keyName, codeProp.getTimeOut (), TimeUnit.MINUTES);
    }
}
