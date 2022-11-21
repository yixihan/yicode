package com.yixihan.yicode.thirdpart;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import org.junit.jupiter.api.Test;

/**
 * description
 *
 * @author yixihan
 * @date 2022/11/21 17:03
 */
public class CustomTest {

    @Test
    public void codeTest () {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 6);
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        captcha.setGenerator (randomGenerator);
        captcha.createCode ();
        System.out.println (captcha.getCode ());
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("D:/code.png");
    }
}
