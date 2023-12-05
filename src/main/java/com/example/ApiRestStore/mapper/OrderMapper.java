package com.example.ApiRestStore.mapper;

import com.example.ApiRestStore.dto.OrderDTO;
import com.example.ApiRestStore.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "idOrder", target = "idOrderDTO")
    @Mapping(source = "activeDate", target = "activeDate")
    @Mapping(source = "client", target = "client")
    @Mapping(source = "activeStatus", target = "activeStatus")
    OrderDTO entityToDTO(Order order);

    @Mapping(source = "idOrderDTO", target = "idOrder")
    @Mapping(source = "activeDate", target = "activeDate")
    @Mapping(source = "client", target = "client")
    @Mapping(source = "activeStatus", target = "activeStatus")
    Order dtoToEntity(OrderDTO orderDTO);
}
