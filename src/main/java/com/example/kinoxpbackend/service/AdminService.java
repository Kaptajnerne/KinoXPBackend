package com.example.kinoxpbackend.service;

import com.example.kinoxpbackend.model.Admin;
import com.example.kinoxpbackend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public Admin findById(int adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin) {
        // You can perform additional validation or data manipulation here if needed
        return adminRepository.save(admin);
    }
}

