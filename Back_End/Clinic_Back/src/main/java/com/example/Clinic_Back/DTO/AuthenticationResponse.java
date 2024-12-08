package com.example.Clinic_Back.DTO;


import com.example.Clinic_Back.Enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String email;
    private UserRole userRole;

    private String userId;


}
