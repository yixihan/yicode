package com.yixihan.yicode.question.openapi.api.rest.admin;

import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.JsonResponse;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyListQuestionReq;
import com.yixihan.yicode.question.openapi.api.vo.request.admin.ModifyQuestionListReq;
import com.yixihan.yicode.question.openapi.api.vo.response.admin.QuestionListVO;
import com.yixihan.yicode.question.openapi.api.vo.response.question.QuestionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 题单 OpenApi
 *
 * @author yixihan
 * @date 2023/3/9 9:27
 */
@Api(tags = "题单 OpenApi")
@RequestMapping("/open/admin/question/list")
public interface QuestionListOpenApi {
    
    @ApiOperation ("添加题单")
    @PostMapping(value = "/create", produces = "application/json")
    JsonResponse<QuestionListVO> createQuestionList (@RequestBody ModifyQuestionListReq req);
    
    @ApiOperation ("修改题单")
    @PostMapping(value = "/modify", produces = "application/json")
    JsonResponse<QuestionListVO> modifyQuestionList (@RequestBody ModifyQuestionListReq req);
    
    @ApiOperation ("删除题单")
    @PostMapping(value = "/del", produces = "application/json")
    void delQuestionList (@RequestParam("id") Long id);
    
    @ApiOperation ("添加题单题目")
    @PostMapping(value = "/question/add", produces = "application/json")
    JsonResponse<QuestionVO> addListQuestion (@RequestBody ModifyListQuestionReq req);
    
    @ApiOperation ("删除题单题目")
    @PostMapping(value = "/question/del", produces = "application/json")
    void delListQuestion (@RequestBody ModifyListQuestionReq req);
    
    @ApiOperation ("分页搜索题单")
    @PostMapping(value = "/page", produces = "application/json")
    JsonResponse<PageVO<QuestionListVO>> questionListPage (@RequestBody PageReq req);
    
    @ApiOperation ("列表搜索题单")
    @PostMapping(value = "/list", produces = "application/json")
    JsonResponse<List<QuestionListVO>> questionListList ();
    
    @ApiOperation ("查看题单题目内容")
    @PostMapping(value = "/question/page", produces = "application/json")
    JsonResponse<PageVO<QuestionVO>> questionPage (@RequestParam("id") Long id);
}
