package com.murphy.model.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class    UserLoginDTO {

    private String username;

    private String password;


    private Boolean rememberMe = false;
}
