package com.yixihan.yicode.run.api.reset;

import com.yixihan.yicode.common.util.ApiResult;
import com.yixihan.yicode.run.api.dto.request.CodeRunDtoReq;
import com.yixihan.yicode.run.api.dto.response.CodeRunDtoResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description
 *
 * @author yixihan
 * @date 2023/1/10 9:53
 */
@Api(tags = "代码运行 api")
@RequestMapping("/run")
public interface CodeRunApi {
    
    @PostMapping("/code")
    ApiResult<CodeRunDtoResult> runCode (@RequestBody CodeRunDtoReq dtoReq);
}
