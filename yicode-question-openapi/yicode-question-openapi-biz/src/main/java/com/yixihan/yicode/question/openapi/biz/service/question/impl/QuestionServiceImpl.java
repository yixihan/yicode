package com.yixihan.yicode.question.openapi.biz.service.question.impl;

import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.LikeReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.ModifyQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.question.QueryQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionDetailVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.LikeService;
import com.yixihan.yicode.question.openapi.biz.service.message.UserMsgService;
import com.yixihan.yicode.question.openapi.biz.service.question.QuestionService;
import com.yixihan.yicode.question.openapi.biz.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:24
 */
@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    
    @Resource
    private UserService userService;
    
    @Resource
    private UserMsgService msgService;
    
    @Resource
    private LikeService likeService;
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    @Override
    public CommonVO<Boolean> addQuestion(ModifyQuestionReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> modifyQuestion(ModifyQuestionReq req) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> delQuestion(List<Long> questionIdList) {
        return null;
    }
    
    @Override
    public CommonVO<Boolean> likeQuestion(LikeReq req) {
        return null;
    }
    
    @Override
    public QuestionDetailVO questionDetail(Long questionId) {
        return null;
    }
    
    @Override
    public PageVO<QuestionVO> queryQuestion(QueryQuestionReq req) {
        return null;
    }
}
