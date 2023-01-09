package com.yixihan.yicode.message.producer.reset;

import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.message.producer.dto.request.MsgSendDtoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测评系统 api
 *
 * @author yixihan
 * @date 2023/1/9 17:04
 */
@Api(tags = "测评系统 api")
@RequestMapping("/task")
public interface TaskApi {
    
    @ApiOperation("发送任务")
    @PostMapping(value = "/send", produces = "application/json")
    ApiResult<CommonDtoResult<Boolean>> send (@RequestBody MsgSendDtoReq dtoReq);
}
