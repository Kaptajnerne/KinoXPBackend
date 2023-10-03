package com.example.kinoxpbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {
    @Id
    private int adminID;
    private String username;
    private String password;
}
