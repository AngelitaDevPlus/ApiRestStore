package com.example.ApiRestStore.controllerTest;

import com.example.ApiRestStore.controller.ClientController;
import com.example.ApiRestStore.dto.ClientDTO;
import com.example.ApiRestStore.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ControllerTest {

    private final ObjectMapper objectMapper;
    @MockBean
    private ClientService clientService;

    private final MockMvc mockMvc;

    @Autowired
    public ControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void shouldCreateClient() throws Exception {
        ClientDTO mockClientDTO = new ClientDTO(1L, "Angelita", "ange@egg.com.pe",
                "678910", true);

        when(clientService.createClient(any(ClientDTO.class))).thenReturn(mockClientDTO);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockClientDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idClientDTO").value(1L))
                .andExpect(jsonPath("$.name").value("Angelita"))
                .andExpect(jsonPath("$.email").value("ange@egg.com.pe"))
                .andExpect(jsonPath("$.phoneNumber").value("678910"))
                .andExpect(jsonPath("$.activeStatus").value(true));
    }

    @Test
    public void shouldListAllClients() throws Exception {
        List<ClientDTO> mockAllClients = new ArrayList<>();
        mockAllClients.add(new ClientDTO(3L, "Angelita", "ange@egg.com.pe",
                "141516", true));
        mockAllClients.add(new ClientDTO(4L, "Angelita", "ange@egg.com.pe",
                "141516", true));
        mockAllClients.add(new ClientDTO(5L, "Angelita", "ange@egg.com.pe",
                "141516", true));

        when(clientService.findAllClients()).thenReturn(mockAllClients);

        mockMvc.perform(get("/api/clients"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    public void shouldListOneClient() throws Exception {
        when(clientService.findById(any(Long.class)))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    return new ClientDTO(id, "Angelita", "ange@egg.com.pe",
                            "678910", true);
                });

        mockMvc.perform(get("/api/2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClientDTO").value(2L))
                .andExpect(jsonPath("$.name").value("Angelita"))
                .andExpect(jsonPath("$.email").value("ange@egg.com.pe"))
                .andExpect(jsonPath("$.phoneNumber").value("678910"))
                .andExpect(jsonPath("$.activeStatus").value(true));
    }

    @Test
    public void shouldUpdateAClient() throws Exception {
        Long idClientDTO = 6L;
        ClientDTO mockClientDTO = new ClientDTO(idClientDTO, "Angelita", "ange@egg.com.pe",
                "678910", true);

        when(clientService.updateClient(any(ClientDTO.class), eq(idClientDTO))).thenReturn(mockClientDTO);

        mockMvc.perform(put("/api/clients/6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idClientDTO\":6,\"name\":\"Angelita\",\"email\":\"ange@egg.com.pe\",\"phoneNumber\":\"678910\",\"activeStatus\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClientDTO").value(idClientDTO))
                .andExpect(jsonPath("$.name").value("Angelita"))
                .andExpect(jsonPath("$.email").value("ange@egg.com.pe"))
                .andExpect(jsonPath("$.phoneNumber").value("678910"))
                .andExpect(jsonPath("$.activeStatus").value(true));

        verify(clientService).updateClient(eq(mockClientDTO), eq(idClientDTO));
    }

    @Test
    public void shouldDeactivateClientById() throws Exception {
        Long idClientDTO = 7L;

        mockMvc.perform(put("/api/deactivateClient/7", idClientDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(clientService).changeActiveStatus(eq(idClientDTO));
    }
}
