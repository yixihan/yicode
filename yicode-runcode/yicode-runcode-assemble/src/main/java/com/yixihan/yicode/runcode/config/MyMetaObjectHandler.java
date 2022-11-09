package com.yixihan.yicode.runcode.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yixihan.yicode.common.util.SnowFlake;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MP 自动插入
 *
 * @author yixihan
 * @date 2022/11/9 9:49
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "replyId", Long.class, SnowFlake.nextId ());
        this.strictInsertFill(metaObject, "rootId", Long.class, SnowFlake.nextId ());
        this.strictInsertFill(metaObject, "labelId", Long.class, SnowFlake.nextId ());
        this.strictInsertFill(metaObject, "noteId", Long.class, SnowFlake.nextId ());
        this.strictInsertFill(metaObject, "questionId", Long.class, SnowFlake.nextId ());
        this.strictInsertFill(metaObject, "labelId", Long.class, SnowFlake.nextId ());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
