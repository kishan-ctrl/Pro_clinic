package com.example.Clinic_Back.DTO;


import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;

}
