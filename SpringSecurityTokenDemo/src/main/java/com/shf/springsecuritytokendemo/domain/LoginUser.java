package com.shf.springsecuritytokendemo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义UserDetails对象的实现类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permissions;

    public LoginUser(User user,List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    /**
     * 返回权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        把permission中String类型的权限信息封装成SimpleGrantedAuthentication
//        ArrayList<GrantedAuthority> newList = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            newList.add(authority);
//        }

        if (authorities != null) {
            return authorities;
        }

//        把permission中String类型的权限信息封装成SimpleGrantedAuthentication
        authorities = permissions
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return authorities;
    }

    /**
     * 框架自动调用LoginUser.getPassword（）方法获取用户名
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
