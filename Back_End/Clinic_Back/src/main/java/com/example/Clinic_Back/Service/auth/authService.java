package com.example.Clinic_Back.Service.auth;

import com.example.Clinic_Back.DTO.SignupRequest;
import com.example.Clinic_Back.DTO.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface authService {

    static UserDetails loadUserByUsername(String username) {
        return null;
    }

    UserDto createCustomer(SignupRequest signupRequest);
    boolean hasCustomerWithEmail(String email);

    List<UserDto> getAllStudents();
}
