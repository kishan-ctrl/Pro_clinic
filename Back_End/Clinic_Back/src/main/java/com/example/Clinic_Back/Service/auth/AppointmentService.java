package com.example.Clinic_Back.Service.auth;

import com.example.Clinic_Back.Entity.Appointment;
import com.example.Clinic_Back.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        return null; // Return null if the appointment does not exist
    }

    // Get appointment by ID
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id).orElse(null); // Return null if not found
    }

    public boolean deleteAppointment(String id) {
        if (appointmentRepository.existsById(id)) { // Ensure the ID exists in the repository
            appointmentRepository.deleteById(id);   // Delete the appointment
            return true;                            // Return true if successful
        }
        return false;                               // Return false if the ID is not found
    }


}
