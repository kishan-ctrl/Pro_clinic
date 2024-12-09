package com.example.Clinic_Back.Service.auth;

import com.example.Clinic_Back.Entity.Appointment;
import com.example.Clinic_Back.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Create an appointment
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Update an existing appointment
    public Appointment updateAppointment(String id, Appointment updatedAppointment) {
        Optional<Appointment> existingAppointmentOptional = appointmentRepository.findById(id);

        if (existingAppointmentOptional.isPresent()) {
            Appointment existingAppointment = existingAppointmentOptional.get();

            // Update specific fields
            existingAppointment.setUserName(updatedAppointment.getUserName());
            existingAppointment.setUserEmail(updatedAppointment.getUserEmail());
            existingAppointment.setPhoneNumber(updatedAppointment.getPhoneNumber());
            existingAppointment.setDate(updatedAppointment.getDate());
            existingAppointment.setReason(updatedAppointment.getReason());

            // Save the updated appointment
            return appointmentRepository.save(existingAppointment);
        }

        return null;
    }

    // Get appointment by ID
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public boolean deleteAppointment(String id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
