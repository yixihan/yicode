package com.yixihan.yicode.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化配置类
 *
 * @author yixihan
 * @date 2022-10-22-16:03
 */
@Configuration
public class WebMvcConfigurerConfig extends WebMvcConfigurationSupport {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        // lambda 会报错
        registry.addConverter (new Converter<String, Date> () {
            @Override
            public Date convert( String stringDate) {
                SimpleDateFormat simpleDateFormat;
                if (stringDate.contains (" ")) {
                    simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                } else {
                    simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
                }
                try {
                    return simpleDateFormat.parse (stringDate);
                } catch (ParseException e) {
                    e.printStackTrace ();
                }
                return null;
            }


        });
    }
}
