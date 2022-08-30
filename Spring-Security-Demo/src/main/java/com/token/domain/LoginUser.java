package com.token.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 *   @author:Nico
 *   @create date:2022/8/15 0015 10:31
 *   @mail:hjx674894982@gmail.com
 *
 *
 *
 *
 */

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permissions;

    public LoginUser(User user, List<String> permissions){
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false)//不进行序列化
    private List<SimpleGrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities!=null)
            return authorities;
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
//        authorities = new ArrayList<>();
//        for (String permission : permissions) {
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            newList.add(authority);
//        }
//        return authorities;
        authorities =
                permissions.stream().
                map(SimpleGrantedAuthority::new).
                collect(Collectors.toList());
        return authorities;
    }

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
