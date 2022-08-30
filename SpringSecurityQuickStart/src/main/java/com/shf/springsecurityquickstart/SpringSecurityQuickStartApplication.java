package com.shf.springsecurityquickstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringSecurityQuickStartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringSecurityQuickStartApplication.class, args);
        System.out.println(111);
    }

}
