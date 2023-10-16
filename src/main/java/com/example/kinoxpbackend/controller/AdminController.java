package com.example.kinoxpbackend.controller;

import com.example.kinoxpbackend.dto.AdminLoginRequestDTO;
import com.example.kinoxpbackend.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.kinoxpbackend.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admins")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequestDTO loginRequest) {
        Admin admin = adminRepository.findByUsername(loginRequest.getUsername());

        if (admin == null || !admin.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> findAdminById(@PathVariable int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<Admin>> findAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return ResponseEntity.ok().body(admins);
    }

    @PostMapping()
    public ResponseEntity<String> createAdmin(@RequestBody Admin admin) {
        try {
            if (adminRepository.findByUsername(admin.getUsername()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
            }
            adminRepository.save(admin);
            return ResponseEntity.ok("Admin created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating admin");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin updatedAdmin) {
        Optional<Admin> existingAdmin = adminRepository.findById(id);

        if (existingAdmin.isPresent()) {
            updatedAdmin.setAdminID(id);
            updatedAdmin.setUsername(updatedAdmin.getUsername());
            updatedAdmin.setPassword(updatedAdmin.getPassword());
            Admin savedAdmin = adminRepository.save(updatedAdmin);
            return ResponseEntity.ok().body(savedAdmin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            adminRepository.delete(admin.get());
            return ResponseEntity.ok("Admin deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting admin");
        }
    }
}