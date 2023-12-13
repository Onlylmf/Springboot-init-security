package com.murphy.config;


import com.murphy.model.dto.user.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class JwtUser {

    private UserDTO user;

    private String token;


    public JwtUser(String token,UserDTO user){
        this.token = token;
        this.user = user;
    }
}
