package com.murphy.model.vo;

import com.murphy.model.entity.sys.SysRole;
import com.murphy.model.entity.sys.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Data
@EqualsAndHashCode(callSuper=true)
@Accessors(chain = true)
public class LoginUserVO extends SysUser implements UserDetails {

    private static final long serialVersionUID = 1L;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        collection.add(new SimpleGrantedAuthority(getId()));
        return collection;
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
        return !getDelFlag();
    }
}