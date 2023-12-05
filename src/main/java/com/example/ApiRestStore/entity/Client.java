package com.example.ApiRestStore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String name;
    private String email;
    private String phoneNumber;
    private Boolean activeStatus; //Active or Inactive instead of Delete

    public Client() {
    }

    public Client(String name, String email, String phoneNumber, Boolean activeStatus) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activeStatus = activeStatus;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", activeStatus=" + activeStatus + '}';
    }
}
    