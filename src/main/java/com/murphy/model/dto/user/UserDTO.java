package com.murphy.model.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String username;

    private String password;

    private String nickname;

    private String headImgUrl;

    private String phone;

    private String email;

    private Integer sex;

    private List<String> roles;
}
