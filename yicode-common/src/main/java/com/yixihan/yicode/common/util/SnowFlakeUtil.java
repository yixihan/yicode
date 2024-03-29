package com.yixihan.yicode.common.util;

import com.yixihan.yicode.common.exception.BizException;

/**
 * 雪花算法
 *
 * @author yixihan
 * @date 2022-11-12-14:15
 */
public class SnowFlakeUtil {
    
    /**
     * 起始的时间戳（可设置当前时间之前的邻近时间）
     */
    private static final long START_STAMP = 1480166465631L;
    
    /**
     * 序列号占用的位数
     */
    private static final long SEQUENCE_BIT = 12;
    
    /**
     * 机器标识占用的位数
     */
    private static final long MACHINE_BIT = 5;
    
    /**
     * 数据中心占用的位数
     */
    private static final long DATA_CENTER_BIT = 5;
    
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    
    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    
    private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    
    private static final long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;
    
    /**
     * 数据中心ID(0~31)
     */
    private static final long DATA_CENTER_ID = 11;
    
    /**
     * 工作机器ID(0~31)
     */
    private static final long MACHINE_ID = 11;
    
    /**
     * 毫秒内序列(0~4095)
     */
    private static long sequence = 0L;
    
    /**
     * 上次生成ID的时间截
     */
    private static long lastStamp = -1L;
    
    private SnowFlakeUtil() {
    }

    /**
     * 产生下一个ID
     */
    public static synchronized long nextId() {
        long currStamp = getNewStamp();
        if (currStamp < lastStamp) {
            throw new BizException ("Clock moved backwards.  Refusing to generate id");
        }

        if (currStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                //阻塞到下一个毫秒,获得新的时间戳
                currStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStamp;

        // 移位并通过或运算拼到一起组成64位的ID
        //时间戳部分
        return (currStamp - START_STAMP) << TIMESTAMP_LEFT
                //数据中心部分
                | DATA_CENTER_ID << DATA_CENTER_LEFT
                //机器标识部分
                | MACHINE_ID << MACHINE_LEFT
                //序列号部分
                | sequence;
    }

    private static long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private static long getNewStamp() {
        return System.currentTimeMillis();
    }
}
