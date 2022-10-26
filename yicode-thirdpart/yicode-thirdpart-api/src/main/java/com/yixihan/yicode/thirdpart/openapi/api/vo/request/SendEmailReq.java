package com.yixihan.yicode.thirdpart.openapi.api.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yixihan
 * @date 2022-10-24-20:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮件发送请求实体类")
public class SendEmailReq {

    private String email;


}
