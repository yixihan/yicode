package com.yixihan.yicode.runcode.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.runcode.biz.service.QuestionService;
import com.yixihan.yicode.runcode.dal.mapper.QuestionMapper;
import com.yixihan.yicode.runcode.dal.pojo.Question;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-24
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
