package com.yixihan.yicode.question.openapi.biz.feign.config;

import org.springframework.web.context.request.RequestAttributes;

/**
 * description
 *
 * @author yixihan
 * @date 2023/4/9 14:54
 */
@SuppressWarnings("all")
public class NonWebRequestAttributes implements RequestAttributes {

    public NonWebRequestAttributes(){

    }
    @Override
    public Object getAttribute(String name, int scope){return null;}

    @Override
    public void setAttribute(String name, Object value, int scope) {

    }
    @Override
    public void removeAttribute(String name, int scope){}
    @Override
    public String[] getAttributeNames(int scope){return new String[0];}

    @Override
    public void registerDestructionCallback(String name, Runnable callback, int scope) {

    }

    @Override
    public Object resolveReference(String key) {
        return null;
    }

    @Override
    public String getSessionId() {
        return null;
    }

    @Override
    public Object getSessionMutex() {
        return null;
    }
}
