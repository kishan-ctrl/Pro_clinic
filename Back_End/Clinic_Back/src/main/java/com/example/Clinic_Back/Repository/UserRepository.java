package com.example.Clinic_Back.Repository;

import com.example.Clinic_Back.Entity.User;
import com.example.Clinic_Back.Enums.UserRole;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);

    List<User> findByUserRole(UserRole userRole);

}
