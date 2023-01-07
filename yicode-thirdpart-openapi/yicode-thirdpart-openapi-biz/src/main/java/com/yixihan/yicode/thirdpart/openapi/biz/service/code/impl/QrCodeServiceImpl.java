package com.yixihan.yicode.thirdpart.openapi.biz.service.code.impl;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.thirdpart.openapi.biz.service.code.QrCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 二维码 服务实现类
 *
 * @author yixihan
 * @date 2022/12/26 14:41
 */
@Slf4j
@Service
public class QrCodeServiceImpl implements QrCodeService {
    @Override
    public void createCode(HttpServletResponse response, String param) {
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        try {
            String dir = System.getProperty ("user.dir");
            QrConfig config = QrConfig.create ()
                    .setImg (dir + "/logo/logo.png")
                    .setWidth (300)
                    .setHeight (300)
                    .setErrorCorrection (ErrorCorrectionLevel.H);
            
            QrCodeUtil.generate(
                    param, config,
                    ImgUtil.IMAGE_TYPE_PNG,
                    response.getOutputStream ()
            );
        } catch (IOException e) {
            throw new BizException (e);
        }
    }
}
