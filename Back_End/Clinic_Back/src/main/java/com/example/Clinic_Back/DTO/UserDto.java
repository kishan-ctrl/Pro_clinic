package com.example.Clinic_Back.DTO;


import com.example.Clinic_Back.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;

    private String name;

    private String email;

    private String NIC;

    private Date DateOfBirth;

    private UserRole userrole;

}
