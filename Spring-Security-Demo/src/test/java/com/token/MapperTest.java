package com.token;

import com.token.domain.User;
import com.token.mapper.MenuMapper;
import com.token.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

/*
 *   @author:Nico
 *   @create date:2022/8/12 0012 16:19
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */
/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MenuMapper menuMapper;
    @Test
    public void testSelectPermsByUserId(){
        List<String> list = menuMapper.selectPermsByUserId(2L);
        System.out.println(list);
    }

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void TestBCryptPasswordEncoder(){
        System.out.println(passwordEncoder.
                matches("1234", "$2a$10$7tMBiPJR9GwAFbmKyCxDGOJLElIOt2eEboIn42OMrY3TEPaOW78Gm"));
//        String encode = passwordEncoder.encode("1234");
//        String encode2 = passwordEncoder.encode("1234");
//        System.out.println(encode);
//        System.out.println(encode2);

    }
}