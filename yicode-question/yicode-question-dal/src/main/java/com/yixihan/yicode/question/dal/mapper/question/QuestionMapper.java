package com.yixihan.yicode.question.dal.mapper.question;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixihan.yicode.question.api.dto.request.admin.AdminDataDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QueryQuestionDtoReq;
import com.yixihan.yicode.question.api.dto.request.question.QuestionDetailDtoReq;
import com.yixihan.yicode.question.api.dto.response.admin.BrokenDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.admin.CommitDataDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionCountDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDetailDtoResult;
import com.yixihan.yicode.question.api.dto.response.question.QuestionDtoResult;
import com.yixihan.yicode.question.dal.pojo.question.Question;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 问题表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    
    /**
     * 获取问题详情
     *
     * @param dtoReq 请求参数
     * @return {@link QuestionDetailDtoResult}
     */
    QuestionDetailDtoResult questionDetail (@Param ("params")QuestionDetailDtoReq dtoReq);
    
    /**
     * 搜索问题
     *
     * @param dtoReq 请求参数
     * @param page 分页参数
     * @return {@link QuestionDtoResult}
     */
    Page<QuestionDtoResult> queryQuestion(@Param ("params") QueryQuestionDtoReq dtoReq,
                                          @Param ("page") Page<QuestionDtoResult> page);
    
    /**
     * 管理中心-查看网址数据-代码提交数&代码通过数
     *
     * @param dtoReq 请求参数
     * @return {@link BrokenDataDtoResult}
     */
    @MapKey ("month")
    Map<String, BrokenDataDtoResult> brokenCodeData(@Param ("params") AdminDataDtoReq dtoReq);
    
    /**
     * 管理中心-查看网址数据-新增评论数-父评论
     *
     * @param dtoReq 请求参数
     * @return {@link BrokenDataDtoResult}
     */
    @MapKey ("month")
    Map<String, BrokenDataDtoResult> brokenCommentRootData(@Param ("params") AdminDataDtoReq dtoReq);
    
    /**
     * 管理中心-查看网址数据-新增评论数-子评论
     *
     * @param dtoReq 请求参数
     * @return {@link BrokenDataDtoResult}
     */
    @MapKey ("month")
    Map<String, BrokenDataDtoResult> brokenCommentReplyData(@Param ("params") AdminDataDtoReq dtoReq);
    
    /**
     * 管理中心-查看网址数据-新增题解数
     *
     * @param dtoReq 请求参数
     * @return {@link BrokenDataDtoResult}
     */
    @MapKey ("month")
    Map<String, BrokenDataDtoResult> brokenNoteData(@Param ("params") AdminDataDtoReq dtoReq);
    
    /**
     * 管理中心-查看网址数据-新增用户数
     *
     * @param dtoReq 请求参数
     * @return {@link BrokenDataDtoResult}
     */
    @MapKey ("month")
    Map<String, BrokenDataDtoResult> brokenUserData(@Param ("params") AdminDataDtoReq dtoReq);
    
    /**
     * 管理中心-代码提交数据
     *
     * @param dtoReq 请求参数
     * @return {@link CommitDataDtoResult}
     */
    CommitDataDtoResult commitData(@Param ("params") AdminDataDtoReq dtoReq);
    
    /**
     * 获取问题数量
     *
     * @return {@link QuestionCountDtoResult}
     */
    QuestionCountDtoResult questionCount();
}
