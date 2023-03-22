package com.yixihan.yicode.question.openapi.biz.service.admin;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyListQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyQuestionListReq;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.QueryQuestionListReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.CollectionVO;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.QuestionListVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;

import java.util.List;

/**
 * 题单 服务类
 *
 * @author yixihan
 * @date 2023/3/9 9:43
 */
public interface QuestionListService {
    
    /**
     * 添加题单
     *
     * @param req 请求参数
     * @return {@link QuestionListVO}
     */
    QuestionListVO createQuestionList(ModifyQuestionListReq req);
    
    /**
     * 修改题单
     *
     * @param req 请求参数
     * @return {@link QuestionListVO}
     */
    QuestionListVO modifyQuestionListReq(ModifyQuestionListReq req);
    
    /**
     * 删除题单
     *
     * @param id 题单 id
     */
    void delQuestionList(Long id);
    
    /**
     * 添加题单题目
     *
     * @param req 请求参数
     * @return {@link CollectionVO}
     */
    QuestionVO addListQuestion(ModifyListQuestionReq req);
    
    /**
     * 删除题单题目
     *
     * @param req 请求参数
     */
    void delListQuestion(ModifyListQuestionReq req);
    
    /**
     * 分页搜索题单
     *
     * @param req 请求参数
     * @return {@link QuestionListVO}
     */
    PageVO<QuestionListVO> questionListPage(PageReq req);
    
    /**
     * 列表搜索题单
     *
     * @return {@link QuestionListVO}
     */
    List<QuestionListVO> questionListList();
    
    /**
     * 查看题单题目内容
     *
     * @param req 请求参数
     * @return {@link QuestionVO}
     */
    PageVO<QuestionVO> questionPage(QueryQuestionListReq req);
}
