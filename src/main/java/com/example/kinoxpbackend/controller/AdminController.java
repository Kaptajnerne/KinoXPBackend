package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.dto.AdminLoginRequest;
import com.example.kinoxpbackend.model.Admin;
import com.example.kinoxpbackend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.kinoxpbackend.repository.AdminRepository;

import java.util.List;

@RestController
@RequestMapping("/admins")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequest loginRequest) {
        Admin admin = adminService.findByUsername(loginRequest.getUsername());

        if (admin == null || !admin.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Successful login
        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> findAdminById(@PathVariable int id) {
        Admin admin = adminService.findById(id);

        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Admin>> findAllAdmins() {
        List<Admin> admins = adminService.findAll();

        if (!admins.isEmpty()) {
            return ResponseEntity.ok(admins);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping()
    public ResponseEntity<String> createAdmin(@RequestBody Admin admin) {
        try {
            if (adminService.findByUsername(admin.getUsername()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
            }

            adminService.createAdmin(admin);
            return ResponseEntity.ok("Admin created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating admin");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdmin(@PathVariable int id, @RequestBody Admin updatedAdmin) {
        try {
            // Check if the admin exists
            Admin existingAdmin = adminService.findById(id);

            if (existingAdmin == null) {
                return ResponseEntity.notFound().build();
            }

            // Update the admin properties as needed
            existingAdmin.setUsername(updatedAdmin.getUsername());
            existingAdmin.setPassword(updatedAdmin.getPassword());

            adminRepository.save(existingAdmin);

            return ResponseEntity.ok("Admin updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating admin");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable int id) {
        try {
            // Check if the admin exists
            Admin existingAdmin = adminService.findById(id);

            if (existingAdmin == null) {
                return ResponseEntity.notFound().build();
            }

            adminRepository.delete(existingAdmin);

            return ResponseEntity.ok("Admin deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting admin");
        }
    }



}

