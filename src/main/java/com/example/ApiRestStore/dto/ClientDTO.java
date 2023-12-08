package com.example.ApiRestStore.dto;

import java.util.Objects;

public class ClientDTO {

    private Long idClientDTO;
    private String name;
    private String email;
    private String phoneNumber;
    private Boolean activeStatus;

    public ClientDTO() {
    }

    public ClientDTO(Long idClientDTO, String name, String email, String phoneNumber, Boolean activeStatus) {
        this.idClientDTO = idClientDTO;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activeStatus = activeStatus;
    }

    public Long getIdClientDTO() {
        return idClientDTO;
    }

    public void setIdClientDTO(Long idClientDTO) {
        this.idClientDTO = idClientDTO;
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

    public Boolean getActiveStatus() { return activeStatus; }

    public void setActiveStatus(Boolean activeStatus) { this.activeStatus = activeStatus; }

    @Override
    public String toString() {
        return "ClientDTO{" + "idClientDTO=" + idClientDTO + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(idClientDTO, clientDTO.idClientDTO) && Objects.equals(name, clientDTO.name) && Objects.equals(email, clientDTO.email) && Objects.equals(phoneNumber, clientDTO.phoneNumber) && Objects.equals(activeStatus, clientDTO.activeStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClientDTO, name, email, phoneNumber, activeStatus);
    }
}
