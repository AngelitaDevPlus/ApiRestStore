package com.example.ApiRestStore.dto;

import com.example.ApiRestStore.entity.Client;
import java.time.LocalDate;

public class OrderDTO {
    
    private Long idOrderDTO;
    private LocalDate activeDate;
    private Client client;
    private Boolean activeStatus;

    public OrderDTO() {
    }

    public OrderDTO(Long idOrderDTO, LocalDate activeDate, Client client, Boolean activeStatus) {
        this.idOrderDTO = idOrderDTO;
        this.activeDate = activeDate;
        this.client = client;
        this.activeStatus = activeStatus;
    }

    public Long getIdOrderDTO() {
        return idOrderDTO;
    }

    public void setIdOrderDTO(Long idOrderDTO) {
        this.idOrderDTO = idOrderDTO;
    }

    public LocalDate getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(LocalDate activeDate) {
        this.activeDate = activeDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "idOrderDTO=" + idOrderDTO + ", activeDate=" + activeDate + ", client=" + client + ", activeStatus=" + activeStatus + '}';
    }
}
