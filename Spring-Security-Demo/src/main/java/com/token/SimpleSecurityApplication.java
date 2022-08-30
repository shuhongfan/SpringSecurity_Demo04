package com.token;

//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/*
 *   @author:Nico
 *   @create date:2022/8/12 0012 14:52
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */
@SpringBootApplication
@MapperScan("com.token.mapper")
public class SimpleSecurityApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SimpleSecurityApplication.class);
        System.out.println(run);
    }
}