package com.yixihan.yicode.user.biz.service.question.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.user.biz.service.question.UserQuestionRecordService;
import com.yixihan.yicode.user.dal.mapper.question.UserQuestionRecordMapper;
import com.yixihan.yicode.user.dal.pojo.question.UserQuestionRecord;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户题目通过记录表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-11-25
 */
@Service
public class UserQuestionRecordServiceImpl extends ServiceImpl<UserQuestionRecordMapper, UserQuestionRecord> implements UserQuestionRecordService {

}
