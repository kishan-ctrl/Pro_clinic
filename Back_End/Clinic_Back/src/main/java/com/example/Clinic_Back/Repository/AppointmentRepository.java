package com.example.Clinic_Back.Repository;

import com.example.Clinic_Back.Entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {


}
