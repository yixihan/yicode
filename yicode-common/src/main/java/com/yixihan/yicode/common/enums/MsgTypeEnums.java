package com.yixihan.yicode.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息类型枚举类
 *
 * @author yixihan
 * @date 2023/1/10 14:52
 */
@AllArgsConstructor
@Getter
public enum MsgTypeEnums {
    
    /**
     * 回复
     */
    REPLY ("REPLY", "回复"),
    
    /**
     * 点赞
     */
    LIKE ("LIKE", "点赞"),
    
    /**
     * 关注
     */
    FOLLOW ("FOLLOW", "关注"),
    
    /**
     * 收藏
     */
    COLLECTION ("COLLECTION", "收藏");
    
    /**
     * 类型
     */
    private final String type;
    
    /**
     * 描述
     */
    private final String description;
    
}
