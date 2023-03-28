package com.yixihan.yicode.question.openapi.web.controller.question;

import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.question.QuestionCaseOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionCaseReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionCaseVO;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public JsonResponse<QuestionCaseVO> addQuestionCase(ModifyQuestionCaseReq req) {
        return JsonResponse.ok (service.addQuestionCase (req));
    }
    
    @Override
    public JsonResponse<QuestionCaseVO> modifyQuestionCase(ModifyQuestionCaseReq req) {
        return JsonResponse.ok (service.modifyQuestionCase (req));
    }
    
    @Override
    public void delQuestionCase(Long id) {
       service.delQuestionCase (id);
    }
    
    @Override
    public JsonResponse<PageVO<QuestionCaseVO>> allQuestionCase(QueryQuestionCaseReq req) {
        return JsonResponse.ok (service.allQuestionCase (req));
    }
}
