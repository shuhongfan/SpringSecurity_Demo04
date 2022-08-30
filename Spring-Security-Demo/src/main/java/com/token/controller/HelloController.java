package com.token.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *   @author:Nico
 *   @create date:2022/8/12 0012 11:23
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */

@RestController
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list1')")
    public String hello(){
        return "hello";
    }
}