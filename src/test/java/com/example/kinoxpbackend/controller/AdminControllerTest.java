package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.dto.AdminLoginRequestDTO;
import com.example.kinoxpbackend.model.Admin;
import com.example.kinoxpbackend.repository.AdminRepository;
import com.example.kinoxpbackend.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @MockBean
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginWithValidCredentials() {
        AdminLoginRequestDTO loginRequest = new AdminLoginRequestDTO();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("password");

        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("password");

        when(adminRepository.findByUsername("admin")).thenReturn(admin);

        ResponseEntity<String> response = adminController.login(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful", response.getBody());

        verify(adminRepository, times(1)).findByUsername("admin");
    }

    @Test
    void testLoginWithInvalidCredentials() {
        AdminLoginRequestDTO loginRequest = new AdminLoginRequestDTO();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("wrongpassword");

        when(adminRepository.findByUsername("admin")).thenReturn(null);

        ResponseEntity<String> response = adminController.login(loginRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password", response.getBody());

        verify(adminRepository, times(1)).findByUsername("admin");
    }

    @Test
    void testFindAdminByIdWithValidId() {
        int adminId = 1;
        Admin admin = new Admin();
        admin.setAdminID(adminId);
        admin.setUsername("admin");

        when(adminRepository.findById(adminId)).thenReturn(Optional.of(admin));

        ResponseEntity<Admin> response = adminController.findAdminById(adminId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());

        verify(adminRepository, times(1)).findById(adminId);
    }

    @Test
    void testFindAdminByIdWithInvalidId() {
        int adminId = 1;

        when(adminRepository.findById(adminId)).thenReturn(null);

        ResponseEntity<Admin> response = adminController.findAdminById(adminId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(adminRepository, times(1)).findById(adminId);
    }

    @Test
    void testFindAllAdmins() {
        Admin admin1 = new Admin();
        admin1.setAdminID(1);
        admin1.setUsername("admin1");

        Admin admin2 = new Admin();
        admin2.setAdminID(2);
        admin2.setUsername("admin2");

        List<Admin> admins = Arrays.asList(admin1, admin2);

        when(adminRepository.findAll()).thenReturn(admins);

        ResponseEntity<List<Admin>> response = adminController.findAllAdmins();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admins, response.getBody());

        verify(adminRepository, times(1)).findAll();
    }

    @Test
    void testCreateAdminSuccess() {
        Admin admin = new Admin();
        admin.setUsername("newadmin");
        admin.setPassword("password");

        when(adminRepository.findByUsername("newadmin")).thenReturn(null);
        doAnswer(invocation -> {
            Admin createdAdmin = invocation.getArgument(0);
            return null;
        }).when(adminRepository).save(any(Admin.class));

        ResponseEntity<String> response = adminController.createAdmin(admin);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admin created successfully", response.getBody());

        verify(adminRepository, times(1)).findByUsername("newadmin");
        verify(adminRepository, times(1)).save(any(Admin.class));
    }


}

