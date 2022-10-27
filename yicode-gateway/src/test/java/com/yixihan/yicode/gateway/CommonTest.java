package com.yixihan.yicode.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @author yixihan
 * @date 2022-10-28-0:35
 */
public class CommonTest {

    @Test
    public void test01() {
        PathMatcher pathMatcher = new AntPathMatcher ();
        System.out.println ("/api/yicode-message-producer/sendmsg/sendMessage/".contains ("/api/yicode-message-producer/sendmsg/sendMessage"));
    }
}
