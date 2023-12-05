package com.example.ApiRestStore.mapper;

import com.example.ApiRestStore.dto.ProductDTO;
import com.example.ApiRestStore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
    @Mapping(source = "idProduct", target = "idProductDTO")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    ProductDTO entityToDTO(Product product);

    @Mapping(source = "idProductDTO", target = "idProduct")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    Product dtoToEntity(ProductDTO productDTO);
}
