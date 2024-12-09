package com.example.Clinic_Back.ControllerTest;


import com.example.Clinic_Back.Controller.AppointmentController;
import com.example.Clinic_Back.Entity.Appointment;
import com.example.Clinic_Back.Service.auth.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    public void testGetAllAppointments() {
        Appointment appointment1 = new Appointment("1", "John Doe", "john@example.com", "1234567890", LocalDate.now(), "Checkup");
        Appointment appointment2 = new Appointment("2", "Jane Doe", "jane@example.com", "0987654321", LocalDate.now(), "Consultation");

        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        List<Appointment> result = appointmentController.getAllAppointments();
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getUserName());
    }

    @Test
    public void testGetAppointmentById() {
        Appointment appointment = new Appointment("1", "John Doe", "john@example.com", "1234567890", LocalDate.now(), "Checkup");

        when(appointmentService.getAppointmentById("1")).thenReturn(appointment);

        ResponseEntity<Appointment> response = appointmentController.getAppointmentById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getUserName());
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = new Appointment("1", "John Doe", "john@example.com", "1234567890", LocalDate.now(), "Checkup");

        when(appointmentService.createAppointment(appointment)).thenReturn(appointment);

        Appointment result = appointmentController.createAppointment(appointment);
        assertEquals("John Doe", result.getUserName());
    }

    @Test
    public void testUpdateAppointment() {
        Appointment updatedAppointment = new Appointment("1", "John Doe", "john@example.com", "1234567890", LocalDate.now(), "Checkup");

        when(appointmentService.updateAppointment("1", updatedAppointment)).thenReturn(updatedAppointment);

        ResponseEntity<Appointment> response = appointmentController.updateAppointment("1", updatedAppointment);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John Doe", response.getBody().getUserName());
    }

    @Test
    public void testDeleteAppointment() {
        when(appointmentService.deleteAppointment("1")).thenReturn(true);

        ResponseEntity<String> response = appointmentController.deleteAppointment("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Appointment deleted successfully", response.getBody());
    }
}