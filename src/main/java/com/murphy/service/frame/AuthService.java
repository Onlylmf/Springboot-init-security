package com.murphy.service.frame;

import com.murphy.config.JwtUser;
import com.murphy.constants.UserConstant;
import com.murphy.model.dto.user.UserDTO;
import com.murphy.model.dto.user.UserLoginDTO;
import com.murphy.model.entity.sys.SysUser;
import com.murphy.model.vo.LoginUserVO;
import com.murphy.service.sys.SysUserService;
import com.murphy.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 用户认证服务
 *
 */
@Service
public class AuthService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * 用户登录认证
     *
     * @param userLogin 用户登录信息
     */
    public JwtUser authLogin(UserLoginDTO userLogin) {
        String userName = userLogin.getUsername();
        String password = userLogin.getPassword();

        // 根据登录名获取用户信息
        SysUser user = sysUserService.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with userName: " + userName);
        }

        // 验证登录密码是否正确。如果正确，则赋予用户相应权限并生成用户认证信息
        if (this.bCryptPasswordEncoder.matches(password, user.getPassword())) {
                List<String> roles = sysUserService.listUserRoles(user.getId());
            // 如果用户角色为空，则默认赋予 ROLE_USER 角色
            if (CollectionUtils.isEmpty(roles)) {
                roles = Collections.singletonList(UserConstant.ROLE_USER);
            }
            // 生成 token
            String token = JwtUtils.generateToken(userName, roles, userLogin.getRememberMe());

            // 认证成功后，设置认证信息到 Spring Security 上下文中
            Authentication authentication = JwtUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 用户信息
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userName);
            userDTO.setEmail(user.getEmail());
            userDTO.setRoles(roles);

            return new JwtUser(token, userDTO);
        }
        throw new BadCredentialsException("The user name or password error.");
    }


    /**
     * 用户退出登录
     *
     * <p>
     * 清除 Spring Security 上下文中的认证信息
     */
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
