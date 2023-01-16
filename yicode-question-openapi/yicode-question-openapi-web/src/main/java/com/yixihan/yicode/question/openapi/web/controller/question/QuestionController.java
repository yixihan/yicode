package com.yixihan.yicode.question.openapi.web.controller.question;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.question.QuestionOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题 前端控制器
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Slf4j
@RestController
public class QuestionController implements QuestionOpenApi {
    
    @Resource
    private QuestionService service;
    
    @Override
    public JsonResponse<CommonVO<Boolean>> addQuestion(ModifyQuestionReq req) {
        return JsonResponse.ok (service.addQuestion (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> modifyQuestion(ModifyQuestionReq req) {
        return JsonResponse.ok (service.modifyQuestion (req));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> delQuestion(List<Long> questionIdList) {
        return JsonResponse.ok (service.delQuestion (questionIdList));
    }
    
    @Override
    public JsonResponse<CommonVO<Boolean>> likeQuestion(LikeReq req) {
        return JsonResponse.ok (service.likeQuestion (req));
    }
    
    @Override
    public JsonResponse<QuestionDetailVO> questionDetail(Long questionId) {
        return JsonResponse.ok (service.questionDetail (questionId));
    }
    
    @Override
    public JsonResponse<PageVO<QuestionVO>> queryQuestion(QueryQuestionReq req) {
        return JsonResponse.ok (service.queryQuestion (req));
    }
}
