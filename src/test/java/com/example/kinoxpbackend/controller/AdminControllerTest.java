package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.controller.AdminController;
import com.example.kinoxpbackend.dto.AdminLoginRequest;
import com.example.kinoxpbackend.model.Admin;
import com.example.kinoxpbackend.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginWithValidCredentials() {
        AdminLoginRequest loginRequest = new AdminLoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("password");

        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("password");

        when(adminService.findByUsername("admin")).thenReturn(admin);

        ResponseEntity<String> response = adminController.login(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful", response.getBody());

        verify(adminService, times(1)).findByUsername("admin");
    }

    @Test
    void testLoginWithInvalidCredentials() {
        AdminLoginRequest loginRequest = new AdminLoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("wrongpassword");

        when(adminService.findByUsername("admin")).thenReturn(null);

        ResponseEntity<String> response = adminController.login(loginRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password", response.getBody());

        verify(adminService, times(1)).findByUsername("admin");
    }

    @Test
    void testFindAdminByIdWithValidId() {
        int adminId = 1;
        Admin admin = new Admin();
        admin.setAdminID(adminId);
        admin.setUsername("admin");

        when(adminService.findById(adminId)).thenReturn(admin);

        ResponseEntity<Admin> response = adminController.findAdminById(adminId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());

        verify(adminService, times(1)).findById(adminId);
    }

    @Test
    void testFindAdminByIdWithInvalidId() {
        int adminId = 1;

        when(adminService.findById(adminId)).thenReturn(null);

        ResponseEntity<Admin> response = adminController.findAdminById(adminId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(adminService, times(1)).findById(adminId);
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

        when(adminService.findAll()).thenReturn(admins);

        ResponseEntity<List<Admin>> response = adminController.findAllAdmins();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admins, response.getBody());

        verify(adminService, times(1)).findAll();
    }

    @Test
    void testCreateAdminSuccess() {
        Admin admin = new Admin();
        admin.setUsername("newadmin");
        admin.setPassword("password");

        when(adminService.findByUsername("newadmin")).thenReturn(null);
        doAnswer(invocation -> {
            Admin createdAdmin = invocation.getArgument(0);
            // Simulate saving the admin object to the database or performing necessary operations
            // Ensure that the createdAdmin object has an ID assigned or any other modifications if necessary
            return null;
        }).when(adminService).createAdmin(any(Admin.class));

        ResponseEntity<String> response = adminController.createAdmin(admin);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admin created successfully", response.getBody());

        verify(adminService, times(1)).findByUsername("newadmin");
        verify(adminService, times(1)).createAdmin(any(Admin.class));
    }


}

