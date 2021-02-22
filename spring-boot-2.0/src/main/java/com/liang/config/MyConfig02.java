package com.liang.config;

import com.liang.domain.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * ConditionalOnMissingBean注解可以用在类或者方法上
 * @author Liangxp
 * @date 2021/02/22 22:40
 */
@Configuration
@ConditionalOnBean(name = "tom")
//@ConditionalOnMissingBean(name = "tom")
@ImportResource("classpath:beans.xml")
public class MyConfig02 {

    @Bean
    public User xiaoliu() {
        return new User("小刘", 24);
    }
}
