package com.yixihan.yicode.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 存在一些情况无法直接Autowired注入我们需要的类，通过此工具类则可以直接获取spring中的bean
 * <p>
 * 根据类名获取实例，例：
 * public StringRedisTemplate stringRedisTemplate = ApplicationContextAwareUtil.getBean("stringRedisTemplate");
 * @author yixihan
 * @date 2022/11/8 15:06
 */
@Component
public class ApplicationContextAwareUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextAwareUtil.applicationContext = applicationContext;

    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        if (applicationContext == null) {
            return null;
        }
        return (T) applicationContext.getBean(name);
    }

}
