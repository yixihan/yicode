package com.yixihan.yicode.user.openapi.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 小凹型类型枚举类
 *
 * @author yixihan
 * @date 2023/1/10 14:52
 */
@AllArgsConstructor
@Getter
public enum MsgTypeEnums {
    
    REPLY ("REPLY", "回复"),
    LIKE ("LIKE", "点赞"),
    FOLLOW ("FOLLOW", "关注"),
    COLLECTION ("COLLECTION", "收藏");
    

    private final String type;
    
    private final String description;
    
}
