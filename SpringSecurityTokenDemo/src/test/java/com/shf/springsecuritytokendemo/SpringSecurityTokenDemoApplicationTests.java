package com.shf.springsecuritytokendemo;

import com.shf.springsecuritytokendemo.domain.Menu;
import com.shf.springsecuritytokendemo.domain.User;
import com.shf.springsecuritytokendemo.mapper.MenuMapper;
import com.shf.springsecuritytokendemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityTokenDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testSelectPermsByUserId() {
        List<String> menus = menuMapper.selectPermsByUserId(2L);
        System.out.println(menus);
    }

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void TestBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        String encode1 = passwordEncoder.encode("123456");
        System.out.println(encode);  // $2a$10$voqe5SmvT5OkCZZ36q9Ph.yz6pWT/MkkKl7VLnp/Un8c5UuToo8bK
        System.out.println(encode1); // $2a$10$nUCg9l.0HczroSVaQXypXeUK9lmVHh.3xtNek7XrIwIFm/Gpug6qu

        System.out.println(passwordEncoder.matches("123456",encode)); // true
    }

}
