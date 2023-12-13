package com.murphy.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String username;

    private String password;

    private String checkPassword;

}
