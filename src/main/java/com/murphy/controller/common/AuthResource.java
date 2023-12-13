package com.murphy.controller.common;

import com.murphy.config.JwtUser;
import com.murphy.constants.ConfigConstant;
import com.murphy.model.dto.user.UserDTO;
import com.murphy.model.dto.user.UserLoginDTO;
import com.murphy.model.dto.user.UserRegisterRequest;
import com.murphy.model.vo.LoginUserVO;
import com.murphy.service.frame.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(tags = "Auth")
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录认证")
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO userLogin) {
        // 用户登录认证
        JwtUser jwtUser = authService.authLogin(userLogin);
        // 认证成功后，将 token 存入响应头中返回
        HttpHeaders httpHeaders = new HttpHeaders();
        // 添加 token 前缀 "Bearer "
        httpHeaders.set(ConfigConstant.TOKEN_HEADER, ConfigConstant.TOKEN_PREFIX + jwtUser.getToken());

        return new ResponseEntity<>(jwtUser.getUser(), httpHeaders, HttpStatus.OK);

    }


    @PostMapping("/logout")
    @ApiOperation(value = "用户退出登录")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.ok().build();
    }
}
