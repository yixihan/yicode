package com.yixihan.yicode.openapi.holder;

import org.springframework.stereotype.Component;

/**
 * 获取登录用户信息
 *
 * @author yixihan
 * @date 2022-10-22-14:45
 */
@Component
public class LoginUserHolder {

//    public UserDTO getCurrentUser(){
//        //从Header中获取用户信息
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        String userStr = request.getHeader("user");
//        JSONObject userJsonObject = new JSONObject (userStr);
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUsername(userJsonObject.getStr("user_name"));
//        userDTO.setId(Convert.toLong(userJsonObject.get("id")));
//        userDTO.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
//        return userDTO;
//    }
}
