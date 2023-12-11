package com.murphy.service.frame;

import com.murphy.model.vo.LoginUserVO;
import com.murphy.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("come in");
        LoginUserVO user = sysUserService.findByUsername(username);
        if (user == null){
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }else if (user.getDelFlag()){
            throw new DisabledException("用户已作废");
        }
        return user;
    }
}
