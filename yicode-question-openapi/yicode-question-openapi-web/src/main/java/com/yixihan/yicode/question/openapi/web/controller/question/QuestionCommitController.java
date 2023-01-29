package com.yixihan.yicode.question.openapi.web.controller.question;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.question.QuestionCommitOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.question.CodeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserQuestionAnswerReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CodeRateVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.CommitRecordVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionAnswerVO;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionCommitService;
import com.yixihan.yicode.question.openapi.web.fallback.CodeRunBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 提交答案 前端控制器
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Slf4j
@RestController
public class QuestionCommitController implements QuestionCommitOpenApi {
    
    @Resource
    private QuestionCommitService service;
    
    @Override
    @SentinelResource(value = "test",
            blockHandler = "blockHandlerTest",
            blockHandlerClass = CodeRunBlockHandler.class
    )
    public void test(CodeReq req) {
        service.test (req);
    }
    
    @Override
    @SentinelResource(value = "commit",
            blockHandler = "blockHandlerCommit",
            blockHandlerClass = CodeRunBlockHandler.class
    )
    public void commit(CodeReq req) {
        service.commit (req);
    }
    
    @Override
    public JsonResponse<PageVO<QuestionAnswerVO>> queryQuestionAnswer(QuestionAnswerReq req) {
        return JsonResponse.ok (service.queryQuestionAnswer (req));
    }
    
    @Override
    public JsonResponse<PageVO<QuestionAnswerVO>> queryUserQuestionAnswer(UserQuestionAnswerReq req) {
        return JsonResponse.ok (service.queryUserQuestionAnswer (req));
    }
    
    @Override
    public JsonResponse<Map<String, List<CommitRecordVO>>> codeCommitCount(String year) {
        return JsonResponse.ok (service.codeCommitCount (year));
    }
    
    @Override
    public JsonResponse<CodeRateVO> codeRate() {
        return JsonResponse.ok (service.codeRate ());
    }
}
