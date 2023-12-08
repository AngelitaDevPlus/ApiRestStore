package com.example.ApiRestStore.serviceTest;

import com.example.ApiRestStore.dto.ClientDTO;
import com.example.ApiRestStore.entity.Client;
import com.example.ApiRestStore.repository.ClientRepository;
import com.example.ApiRestStore.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTest {

    @MockBean
    private ClientRepository clientRepository;

    private final ClientService clientService;

    @Autowired
    public ClientServiceTest(ClientService clientService) {
        this.clientService = clientService;
    }

    @Test
    public void shouldCreateClient() {
        when(clientRepository.save(any(Client.class)))
                .thenAnswer(invocation -> {
                    Client mockClient = new Client("Angelita", "ange@egg.com.pe",
                            "678910", true);
                    mockClient.setIdClient(1L);
                    return mockClient;
                });

        ClientDTO expectedClientDTO = new ClientDTO(1L, "Angelita", "ange@egg.com.pe",
                "678910", true);
        ClientDTO createdClientDTO = clientService.createClient(expectedClientDTO);

        assertNotNull(createdClientDTO.getIdClientDTO());
        assertEquals(expectedClientDTO.getName(), createdClientDTO.getName());
        assertEquals(expectedClientDTO, createdClientDTO);
    }

    @Test
    public void shouldListOneClientById() {
        when(clientRepository.findById(any(Long.class)))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    Client mockClient = new Client(id, "Angelita", "ange@egg.com.pe",
                            "111213", true);
                    return Optional.of(mockClient);
                });

        ClientDTO expectedClientDTO = new ClientDTO(2L, "Angelita", "ange@egg.com.pe",
                "111213", true);
        ClientDTO foundedClientDTO = clientService.findById(2L);

        assertEquals(expectedClientDTO, foundedClientDTO);
    }

    @Test
    public void shouldListAllClients() {
        List<Client> mockAllClients = new ArrayList<>();
        mockAllClients.add(new Client(3L, "Angelita", "ange@egg.com.pe",
                "141516", true));
        mockAllClients.add(new Client(4L, "Angelita", "ange@egg.com.pe",
                "141516", true));
        mockAllClients.add(new Client(5L, "Angelita", "ange@egg.com.pe",
                "141516", true));

        when(clientRepository.findAll()).thenReturn(mockAllClients);

        List<ClientDTO> expectedAllClientsDTO = new ArrayList<>();
        expectedAllClientsDTO.add(new ClientDTO(3L, "Angelita", "ange@egg.com.pe",
                "141516", true));
        expectedAllClientsDTO.add(new ClientDTO(4L, "Angelita", "ange@egg.com.pe",
                "141516", true));
        expectedAllClientsDTO.add(new ClientDTO(5L, "Angelita", "ange@egg.com.pe",
                "141516", true));

        List<ClientDTO> foundAllClientsDTO = clientService.findAllClients();

        assertEquals(expectedAllClientsDTO, foundAllClientsDTO);
    }

    @Test
    public void shouldUpdateAClient() {
        when(clientRepository.findById(any(Long.class)))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    Client mockClient = new Client(id, "Angelita", "ange@egg.com.pe",
                            "171819", true);
                    return Optional.of(mockClient);
                });
        Client mockClient = new Client(6L, "Angelita", "ange@egg.com.pe",
                "171819", true);
        when(clientRepository.save(any(Client.class))).thenReturn(mockClient);

        ClientDTO expectedClientDTO = new ClientDTO(6L, "Angelita", "ange@egg.com.pe",
                "171819", true);
        ClientDTO updatedClientDTO = clientService.updateClient(expectedClientDTO, 6L);

        assertEquals(expectedClientDTO, updatedClientDTO);

    }

    @Test
    public void shouldDeactivateClientById() {
        Long clientId = 7L;
        Client mockClient = new Client(clientId, "Angelita", "ange@egg.com.pe",
                "202122", true);

        when(clientRepository.findById(eq(clientId))).thenReturn(Optional.of(mockClient));

        clientService.changeActiveStatus(clientId);

        verify(clientRepository, times(1)).findById(eq(clientId));
        verify(clientRepository, times(1)).save(mockClient);

        assertFalse(mockClient.getActiveStatus());
    }
}
