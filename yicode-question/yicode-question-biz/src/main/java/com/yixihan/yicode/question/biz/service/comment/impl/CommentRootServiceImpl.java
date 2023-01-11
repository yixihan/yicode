package com.yixihan.yicode.question.biz.service.comment.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.question.biz.service.comment.CommentRootService;
import com.yixihan.yicode.question.dal.mapper.comment.CommentRootMapper;
import com.yixihan.yicode.question.dal.pojo.comment.CommentRoot;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 父评论表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class CommentRootServiceImpl extends ServiceImpl<CommentRootMapper, CommentRoot> implements CommentRootService {

}
