package com.app.laptopshop.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String name;
    private String email;
    private String phone;
    private String password;
}
