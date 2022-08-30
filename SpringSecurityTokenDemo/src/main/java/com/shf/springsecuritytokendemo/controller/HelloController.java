package com.shf.springsecuritytokendemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('system:dept:list')")
//    @PreAuthorize("hasAnyAuthority('admin','test','system:dept:list')")
//    @PreAuthorize("hasRole('system:dept:list')")
//    @PreAuthorize("hasAnyRole('admin','system:dept:list')")
    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    public String hello() {
        return "hello";
    }
}
