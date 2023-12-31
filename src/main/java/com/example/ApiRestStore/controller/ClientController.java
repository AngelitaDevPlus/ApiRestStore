package com.example.ApiRestStore.controller;

import com.example.ApiRestStore.dto.ClientDTO;
import com.example.ApiRestStore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO postClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO getClientById(@PathVariable("id") Long idClientDTO) {
        return clientService.findById(idClientDTO);
    }

    @PutMapping("/clients/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO putClient(@RequestBody ClientDTO clientDTO, @PathVariable("id") Long idClientDTO) {
        return clientService.updateClient(clientDTO, idClientDTO);
    }

    @PutMapping("/deactivateClient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deactivateClient(@PathVariable("id") Long idClientDTO) {
        clientService.changeActiveStatus(idClientDTO);
    }
}
