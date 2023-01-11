package com.yixihan.yicode.question.biz.service.note.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yixihan.yicode.question.biz.service.note.NoteService;
import com.yixihan.yicode.question.dal.mapper.note.NoteMapper;
import com.yixihan.yicode.question.dal.pojo.note.Note;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题题解表 服务实现类
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

}
