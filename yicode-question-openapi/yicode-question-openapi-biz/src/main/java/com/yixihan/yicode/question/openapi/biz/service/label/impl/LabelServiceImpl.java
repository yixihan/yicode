package com.yixihan.yicode.question.openapi.biz.service.label.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.request.PageDtoReq;
import com.yixihan.yicode.common.reset.dto.responce.PageDtoResult;
import com.yixihan.yicode.common.reset.vo.request.PageReq;
import com.yixihan.yicode.common.reset.vo.responce.PageVO;
import com.yixihan.yicode.common.util.PageVOUtil;
import com.yixihan.yicode.question.api.dto.request.label.AddLabelDtoReq;
import com.yixihan.yicode.question.api.dto.response.label.LabelDtoResult;
import com.yixihan.yicode.question.openapi.api.vo.request.label.AddLabelReq;
import com.yixihan.yicode.question.openapi.api.vo.response.label.LabelVO;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelNoteFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.label.LabelQuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.note.NoteFeignClient;
import com.yixihan.yicode.question.openapi.biz.feign.question.question.QuestionFeignClient;
import com.yixihan.yicode.question.openapi.biz.service.label.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签 服务实现类
 *
 * @author yixihan
 * @date 2023/1/13 12:21
 */
@Slf4j
@Service
public class LabelServiceImpl implements LabelService {
    
    @Resource
    private LabelFeignClient labelFeignClient;
    
    @Resource
    private LabelNoteFeignClient labelNoteFeignClient;
    
    @Resource
    private LabelQuestionFeignClient labelQuestionFeignClient;
    
    @Resource
    private NoteFeignClient noteFeignClient;
    
    @Resource
    private QuestionFeignClient questionFeignClient;
    
    
    @Override
    public LabelVO addLabel(AddLabelReq req) {
        // 参数校验 (标签名)
        if (StrUtil.isBlank (req.getLabelName ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 添加标签
        AddLabelDtoReq dtoReq = BeanUtil.toBean (req, AddLabelDtoReq.class);
        LabelDtoResult dtoResult = labelFeignClient.addLabel (dtoReq).getResult ();
        
        return BeanUtil.toBean (dtoResult, LabelVO.class);
    }
    
    @Override
    public void delLabel(List<Long> labelIdList) {
        // 参数校验 (标签 ID)
        if (CollectionUtil.isEmpty (labelIdList)) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 删除标签
        labelFeignClient.delLabel (labelIdList);
    }
    
    @Override
    public List<LabelVO> labelDetail(List<Long> labelIdList) {
        // 参数校验 (标签 ID)
        if (CollectionUtil.isEmpty (labelIdList)) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 获取标签明细
        List<LabelDtoResult> dtoResult = labelFeignClient.labelDetail (labelIdList).getResult ();
        
        return BeanUtil.copyToList (dtoResult, LabelVO.class);
    }
    
    @Override
    public List<LabelVO> noteLabelDetail(Long noteId) {
        if (Boolean.FALSE.equals (noteFeignClient.verifyNote (noteId).getResult ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        List<LabelDtoResult> dtoResultList = labelNoteFeignClient.noteLabelDetail (noteId).getResult ();
    
        return BeanUtil.copyToList (dtoResultList, LabelVO.class);
    }
    
    @Override
    public List<LabelVO> questionLabelDetail(Long questionId) {
        if (Boolean.FALSE.equals (questionFeignClient.verifyQuestion (questionId).getResult ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        List<LabelDtoResult> dtoResultList = labelQuestionFeignClient.questionLabelDetail (questionId).getResult ();
    
        return BeanUtil.copyToList (dtoResultList, LabelVO.class);
    }
    
    @Override
    public PageVO<LabelVO> allLabel(PageReq req) {
        PageDtoReq dtoReq = BeanUtil.toBean (req, PageDtoReq.class);
        PageDtoResult<LabelDtoResult> dtoResult = labelFeignClient.allLabel (dtoReq).getResult ();
        
        return PageVOUtil.pageDtoToPageVO (
                dtoResult,
                o -> BeanUtil.toBean (o, LabelVO.class)
        );
    }
    
    @Override
    public List<LabelVO> allNoteLabel() {
        List<LabelDtoResult> dtoResultList = labelNoteFeignClient.allNoteLabel ().getResult ();
    
        return BeanUtil.copyToList (dtoResultList, LabelVO.class);
    }
    
    @Override
    public List<LabelVO> allQuestionLabel() {
        List<LabelDtoResult> dtoResultList = labelQuestionFeignClient.allQuestionLabel ().getResult ();
    
        return BeanUtil.copyToList (dtoResultList, LabelVO.class);
    }
}
