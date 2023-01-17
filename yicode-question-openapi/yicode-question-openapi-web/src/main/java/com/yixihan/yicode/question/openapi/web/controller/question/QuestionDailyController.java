package com.yixihan.yicode.question.openapi.web.controller.question;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.question.QuestionDailyOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.question.UserDailyQuestionDetailReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDailyVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.UserDailyQuestionVO;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionDailyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 每日一题 前端控制器
 *
 * @author yixihan
 * @date 2023/1/11 10:15
 */
@Slf4j
@RestController
public class QuestionDailyController implements QuestionDailyOpenApi {
    
    @Resource
    private QuestionDailyService service;
    
    @Override
    public JsonResponse<List<QuestionDailyVO>> dailyQuestionDetail(String month) {
        return JsonResponse.ok (service.dailyQuestionDetail (month));
    }
    
    @Override
    public JsonResponse<CommonVO<Integer>> dailyQuestionCount() {
        return JsonResponse.ok (service.dailyQuestionCount ());
    }
    
    @Override
    public JsonResponse<List<UserDailyQuestionVO>> userDailyQuestionDetail(UserDailyQuestionDetailReq req) {
        return JsonResponse.ok (service.userDailyQuestionDetail (req));
    }
}
