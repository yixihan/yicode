package com.yixihan.yicode.thridpart.openapi;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Test;

import java.io.File;

/**
 * description
 *
 * @author yixihan
 * @date 2022/12/26 15:35
 */
public class CustomTest {
    
    @Test
    public void testUrlToFile () {
        String dir = System.getProperty ("user.dir");
        System.out.println (dir + "\\src\\main\\resources\\logo\\logo.png");
        File file = new File (dir + "\\src\\main\\resources\\logo\\logo.png");
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrConfig config = QrConfig.create ()
                .setImg (file)
                .setWidth (300)
                .setHeight (300)
                .setErrorCorrection (ErrorCorrectionLevel.H);
        QrCodeUtil.generate(
                "http://hutool.cn/",
                config,
                FileUtil.file (dir + "/test.jpg"));
    }
}
