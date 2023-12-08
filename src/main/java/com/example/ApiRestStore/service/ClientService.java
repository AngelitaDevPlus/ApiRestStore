package com.example.ApiRestStore.service;

import com.example.ApiRestStore.dto.ClientDTO;
import com.example.ApiRestStore.entity.Client;
import com.example.ApiRestStore.mapper.ClientMapper;
import com.example.ApiRestStore.repository.ClientRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {
    
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }
    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO) {
        
        if (Objects.nonNull(clientDTO)) {
            try {
                validate(clientDTO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Client client = clientMapper.dtoToClient(clientDTO);
            client.setActiveStatus(Boolean.TRUE);
            Client savedClient = clientRepository.save(client);
            return clientMapper.entityToDTO(savedClient);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client not uploaded.");
        }
    }
    
    public ClientDTO findById(Long idClientDTO) {
        Optional<Client> response = clientRepository.findById(idClientDTO);
        
        if (response.isPresent()) {
            Client client = response.get();
            return clientMapper.entityToDTO(client);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client not found.");
        }
    }

    public List<ClientDTO> findAllClients() {
        List<Client> allClients = clientRepository.findAll();
        List<ClientDTO> allClientsDTO = new ArrayList<>();

        for (Client aux: allClients) {
            ClientDTO clientDTO = clientMapper.entityToDTO(aux);
            allClientsDTO.add(clientDTO);
        }
        return allClientsDTO;
    }
    
    @Transactional
    public ClientDTO updateClient(ClientDTO clientDTO, Long idClientDTO) {
       Optional<Client> response = clientRepository.findById(idClientDTO);
        
        if (response.isPresent()) {
            Client client = response.get();
            client.setName(clientDTO.getName());
            client.setEmail(clientDTO.getEmail());
            client.setPhoneNumber(clientDTO.getPhoneNumber());
            Client updatedClient = clientRepository.save(client);
            return clientMapper.entityToDTO(updatedClient);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client not uploaded.");
        }
    }

    @Transactional
    public void changeActiveStatus(Long idClientDTO) {
        Optional<Client> response = clientRepository.findById(idClientDTO);

        if (response.isPresent()) {
            Client client = response.get();
            client.setActiveStatus(false);
            clientRepository.save(client);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client not changed.");
        }
    }
    
    private void validate(ClientDTO clientDTO) throws Exception {
        if (clientDTO.getName() == null || clientDTO.getName().isEmpty()) {
            throw new Exception("The name of the client can't be null.");
        }
        if (clientDTO.getEmail() == null || clientDTO.getEmail().isEmpty()) {
            throw new Exception("The email can't be null.");
        }
        if (clientDTO.getPhoneNumber() == null) {
            throw new Exception("The phone number can't be null.");
        }
    }
}
