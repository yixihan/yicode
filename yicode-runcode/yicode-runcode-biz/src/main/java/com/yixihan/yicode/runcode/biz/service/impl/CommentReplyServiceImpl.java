package com.yixihan.yicode.runcode.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.runcode.biz.service.CommentReplyService;
import com.yixihan.yicode.runcode.dal.mapper.CommentReplyMapper;
import com.yixihan.yicode.runcode.dal.pojo.CommentReply;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 子评论表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2022-10-24
 */
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements CommentReplyService {

}