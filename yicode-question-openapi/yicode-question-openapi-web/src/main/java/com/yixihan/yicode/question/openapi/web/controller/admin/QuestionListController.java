package com.yixihan.yicode.question.openapi.web.controller.admin;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.rest.admin.QuestionListOpenApi;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyListQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyQuestionListReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.QuestionListVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import com.yixihan.yicode.question.openapi.biz.service.admin.QuestionListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题单 前端控制器
 *
 * @author yixihan
 * @date 2023/3/9 9:40
 */
@Slf4j
@RestController
public class QuestionListController implements QuestionListOpenApi {
    
    @Resource
    private QuestionListService service;
    
    @Override
    public JsonResponse<QuestionListVO> createQuestionList(ModifyQuestionListReq req) {
        return JsonResponse.ok (service.createQuestionList (req));
    }
    
    @Override
    public JsonResponse<QuestionListVO> modifyQuestionList(ModifyQuestionListReq req) {
        return JsonResponse.ok (service.modifyQuestionListReq (req));
    }
    
    @Override
    public void delQuestionList(Long id) {
        service.delQuestionList (id);
    }
    
    @Override
    public JsonResponse<QuestionVO> addListQuestion(ModifyListQuestionReq req) {
        return JsonResponse.ok (service.addListQuestion (req));
    }
    
    @Override
    public void delListQuestion(ModifyListQuestionReq req) {
        service.delListQuestion (req);
    }
    
    @Override
    public JsonResponse<PageVO<QuestionListVO>> questionListPage(PageReq req) {
        return JsonResponse.ok (service.questionListPage (req));
    }
    
    @Override
    public JsonResponse<List<QuestionListVO>> questionListList() {
        return JsonResponse.ok (service.questionListList ());
    }
    
    @Override
    public JsonResponse<PageVO<QuestionVO>> questionPage(Long id) {
        return JsonResponse.ok (service.questionPage (id));
    }
}
