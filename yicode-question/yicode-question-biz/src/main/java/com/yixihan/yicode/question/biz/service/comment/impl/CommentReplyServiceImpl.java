package com.yixihan.yicode.question.biz.service.comment.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.question.biz.service.comment.CommentReplyService;
import com.yixihan.yicode.question.dal.mapper.comment.CommentReplyMapper;
import com.yixihan.yicode.question.dal.pojo.comment.CommentReply;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 子评论表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements CommentReplyService {

}
