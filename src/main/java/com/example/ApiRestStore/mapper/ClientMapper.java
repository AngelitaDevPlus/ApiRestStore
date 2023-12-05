package com.example.ApiRestStore.mapper;

import com.example.ApiRestStore.dto.ClientDTO;
import com.example.ApiRestStore.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "idClient", target = "idClientDTO")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    ClientDTO entityToDTO(Client client);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    Client dtoToClient(ClientDTO clientDTO);
}
