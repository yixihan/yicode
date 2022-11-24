package com.yixihan.yicode.thirdpart.biz.service.code.impl;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.thirdpart.api.constant.code.CodeConstant;
import com.yixihan.yicode.thirdpart.api.dto.request.code.CodeValidateDtoReq;
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
    private CodeConstant codeConstant;

    private static final char[] RANDOM_Arr = "1234567890".toCharArray ();

    public String getCode(String keyName) {
        // 生成验证码
        String code = getRandomCode ();

        // 存入 redis
        addRedis (keyName, code);
        return code;
    }

    @Override
    public CommonDtoResult<Boolean> validate(String keyName, String code) {
        // 校验验证码是否过期
        // 校验验证码是否已经过期
        Long expire = stringRedisTemplate.getExpire(keyName);

        if (expire == null || expire < 0L) {
            return new CommonDtoResult<> (false, "验证码已过期");
        }

        boolean flag = code.equals (stringRedisTemplate.opsForValue ().get (keyName));
        if (flag) {
            return new CommonDtoResult<> (true, "验证码校验成功");
        } else {
            return new CommonDtoResult<> (false, "验证码错误");
        }
    }

    @Override
    public void createCode(String code, String uuid) {
        String keyName = String.format (codeConstant.getCommonKey (), uuid);
        // 存入 redis
        addRedis (keyName, code);
    }

    @Override
    public CommonDtoResult<Boolean> validateCode(CodeValidateDtoReq dtoReq) {
        String keyName = String.format (codeConstant.getCommonKey (), dtoReq.getUuid ());
        return validate (keyName, dtoReq.getCode ());
    }

    /**
     * 获取随机验证码, 并存入 redis 中
     *
     * @return code
     */
    private synchronized String getRandomCode() {
        int len = codeConstant.getCodeLen ();
        Random random = new Random ();
        StringBuilder sb = new StringBuilder (len);
        for (int i = 0; i < len; i++) {
            sb.append (RANDOM_Arr[random.nextInt (RANDOM_Arr.length)]);
        }

        return sb.toString ();
    }

    /**
     * 将验证码存入 redis 中， 并设置有效时间
     */
    private void addRedis (String keyName, String code) {
        // 存入 redis
        stringRedisTemplate.opsForValue().set(keyName, code);
        // 设置过期时间
        stringRedisTemplate.expire(keyName, codeConstant.getTimeOut (), TimeUnit.MINUTES);
    }
}
