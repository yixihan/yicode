package com.yixihan.yicode.common.exception;

/**
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *      001：参数格式校验
 *  11: 商品
 *  12: 订单
 *  13: 购物车
 *  14: 物流
 *
 * @author yixihan
 * @date 2022-10-10-13:35
 */
public interface CommonError {

    /**
     * 错误码
     */
    int getErrorCode();

    /**
     * 错误信息
     */
    String getErrorMsg();
}


