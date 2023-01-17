package com.yixihan.yicode.question.openapi.web.controller.question;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.question.QuestionCaseOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCaseVO;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题测试用例管理 前端控制器
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Slf4j
@RestController
public class QuestionCaseController implements QuestionCaseOpenApi {
    
    @Resource
    private QuestionCaseService service;
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addQuestionCase(ModifyQuestionCaseReq req) {
        return JsonResponse.ok (service.addQuestionCase (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> modifyQuestionCase(ModifyQuestionCaseReq req) {
        return JsonResponse.ok (service.modifyQuestionCase (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delQuestionCase(Long id) {
        return JsonResponse.ok (service.delQuestionCase (id));
    }
    
    @Override
    public JsonResponse<List<QuestionCaseVO>> allQuestionCase(Long questionId) {
        return JsonResponse.ok (service.allQuestionCase (questionId));
    }
}
