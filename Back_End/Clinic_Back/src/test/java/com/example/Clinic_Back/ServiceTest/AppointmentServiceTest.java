package com.example.Clinic_Back.ServiceTest;



import com.example.Clinic_Back.Entity.Appointment;
import com.example.Clinic_Back.Repository.AppointmentRepository;
import com.example.Clinic_Back.Service.auth.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAppointments() {
        Appointment appointment1 = new Appointment("1", "John Doe", "john@example.com", "1234567890", LocalDate.now(), "Checkup");
        Appointment appointment2 = new Appointment("2", "Jane Smith", "jane@example.com", "0987654321", LocalDate.now(), "Consultation");

        when(appointmentRepository.findAll()).thenReturn(Arrays.asList(appointment1, appointment2));

        var appointments = appointmentService.getAllAppointments();

        assertEquals(2, appointments.size());
        assertEquals("John Doe", appointments.get(0).getUserName());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment("1", "John Doe", "john@example.com", "1234567890", LocalDate.now(), "Checkup");

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        var savedAppointment = appointmentService.createAppointment(appointment);

        assertNotNull(savedAppointment);
        assertEquals("John Doe", savedAppointment.getUserName());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    public void testUpdateAppointment_Found() {
        Appointment existingAppointment = new Appointment("1", "John Doe", "john@example.com", "1234567890", LocalDate.now(), "Checkup");
        Appointment updatedAppointment = new Appointment("1", "John Doe Updated", "john.updated@example.com", "0987654321", LocalDate.now(), "Follow-Up");

        when(appointmentRepository.findById("1")).thenReturn(Optional.of(existingAppointment));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(updatedAppointment);

        var result = appointmentService.updateAppointment("1", updatedAppointment);

        assertNotNull(result);
        assertEquals("John Doe Updated", result.getUserName());
        verify(appointmentRepository, times(1)).findById("1");
        verify(appointmentRepository, times(1)).save(existingAppointment);
    }

    @Test
    public void testUpdateAppointment_NotFound() {
        Appointment updatedAppointment = new Appointment("1", "John Doe Updated", "john.updated@example.com", "0987654321", LocalDate.now(), "Follow-Up");

        when(appointmentRepository.findById("1")).thenReturn(Optional.empty());

        var result = appointmentService.updateAppointment("1", updatedAppointment);

        assertNull(result);
        verify(appointmentRepository, times(1)).findById("1");
        verify(appointmentRepository, never()).save(any(Appointment.class));
    }

    @Test
    public void testGetAppointmentById_Found() {
        Appointment appointment = new Appointment("1", "John Doe", "john@example.com", "1234567890", LocalDate.now(), "Checkup");

        when(appointmentRepository.findById("1")).thenReturn(Optional.of(appointment));

        var result = appointmentService.getAppointmentById("1");

        assertNotNull(result);
        assertEquals("John Doe", result.getUserName());
        verify(appointmentRepository, times(1)).findById("1");
    }

    @Test
    public void testGetAppointmentById_NotFound() {
        when(appointmentRepository.findById("1")).thenReturn(Optional.empty());

        var result = appointmentService.getAppointmentById("1");

        assertNull(result);
        verify(appointmentRepository, times(1)).findById("1");
    }

    @Test
    public void testDeleteAppointment_Found() {
        when(appointmentRepository.existsById("1")).thenReturn(true);

        var result = appointmentService.deleteAppointment("1");

        assertTrue(result);
        verify(appointmentRepository, times(1)).existsById("1");
        verify(appointmentRepository, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteAppointment_NotFound() {
        when(appointmentRepository.existsById("1")).thenReturn(false);

        var result = appointmentService.deleteAppointment("1");

        assertFalse(result);
        verify(appointmentRepository, times(1)).existsById("1");
        verify(appointmentRepository, never()).deleteById(anyString());
    }
}
