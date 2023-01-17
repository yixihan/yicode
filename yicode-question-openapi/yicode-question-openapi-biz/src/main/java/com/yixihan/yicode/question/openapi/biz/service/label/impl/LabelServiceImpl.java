package com.yixihan.yicode.question.openapi.biz.service.label.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.reset.dto.responce.CommonDtoResult;
import com.yixihan.yicode.common.reset.vo.responce.CommonVO;
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
    public CommonVO<Boolean> addLabel(AddLabelReq req) {
        // 参数校验 (标签名)
        if (StrUtil.isBlank (req.getLabelName ())) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
        
        // 添加标签
        AddLabelDtoReq dtoReq = BeanUtil.toBean (req, AddLabelDtoReq.class);
        CommonDtoResult<Boolean> dtoResult = labelFeignClient.addLabel (dtoReq).getResult ();
    
        // 如果添加失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
    }
    
    @Override
    public CommonVO<Boolean> delLabel(List<Long> labelIdList) {
        // 参数校验 (标签 ID)
        if (CollectionUtil.isEmpty (labelIdList)) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        // 删除标签
        CommonDtoResult<Boolean> dtoResult = labelFeignClient.delLabel (labelIdList).getResult ();
    
        // 如果删除失败, 抛出异常信息
        if (!dtoResult.getData ()) {
            throw new BizException (dtoResult.getMessage ());
        }
        return CommonVO.create (dtoResult);
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
        if (!noteFeignClient.verifyNote (noteId).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        List<LabelDtoResult> dtoResultList = labelNoteFeignClient.noteLabelDetail (noteId).getResult ();
    
        return BeanUtil.copyToList (dtoResultList, LabelVO.class);
    }
    
    @Override
    public List<LabelVO> questionLabelDetail(Long questionId) {
        if (!questionFeignClient.verifyQuestion (questionId).getResult ().getData ()) {
            throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
        }
    
        List<LabelDtoResult> dtoResultList = labelQuestionFeignClient.questionLabelDetail (questionId).getResult ();
    
        return BeanUtil.copyToList (dtoResultList, LabelVO.class);
    }
    
    @Override
    public List<LabelVO> AllNoteLabel() {
        List<LabelDtoResult> dtoResultList = labelNoteFeignClient.allNoteLabel ().getResult ();
    
        return BeanUtil.copyToList (dtoResultList, LabelVO.class);
    }
    
    @Override
    public List<LabelVO> AllQuestionLabel() {
        List<LabelDtoResult> dtoResultList = labelQuestionFeignClient.allQuestionLabel ().getResult ();
    
        return BeanUtil.copyToList (dtoResultList, LabelVO.class);
    }
}
