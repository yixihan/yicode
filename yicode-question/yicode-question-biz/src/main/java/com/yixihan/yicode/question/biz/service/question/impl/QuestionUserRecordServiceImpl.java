package com.yixihan.yicode.question.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.question.biz.service.question.QuestionUserRecordService;
import com.yixihan.yicode.question.dal.mapper.question.QuestionUserRecordMapper;
import com.yixihan.yicode.question.dal.pojo.question.QuestionUserRecord;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户题目通过记录表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class QuestionUserRecordServiceImpl extends ServiceImpl<QuestionUserRecordMapper, QuestionUserRecord> implements QuestionUserRecordService {

}
