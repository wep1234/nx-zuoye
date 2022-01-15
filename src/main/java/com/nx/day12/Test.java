package com.nx.day12;

import com.nx.day12.config.SpringConfiguration;
import com.nx.day12.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author: wep
 * @Since: 2021/5/15 15:06
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = (UserService)context.getBean("userService");
        userService.createSchema();
        userService.getUser();
        try {
            userService.saveUser();
        }catch (Exception e){
            e.printStackTrace();
        }
        userService.getUser();
    }

}
