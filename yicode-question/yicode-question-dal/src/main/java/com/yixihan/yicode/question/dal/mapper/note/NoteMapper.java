package com.yixihan.yicode.question.dal.mapper.note;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixihan.yicode.question.api.dto.request.note.QueryNoteDtoReq;
import com.yixihan.yicode.question.api.dto.response.note.NoteDtoResult;
import com.yixihan.yicode.question.dal.pojo.note.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 问题题解表 Mapper 接口
 * </p>
 *
 * @author yixihan
 * @since 2023-01-11
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {
    
    /**
     * 搜索题解
     *
     * @param dtoReq 请求参数
     * @param page 分页参数
     * @return {@link NoteDtoResult}
     */
    Page<NoteDtoResult> queryNote(@Param ("params") QueryNoteDtoReq dtoReq,
                                  @Param ("page") Page<NoteDtoResult> page);

}
