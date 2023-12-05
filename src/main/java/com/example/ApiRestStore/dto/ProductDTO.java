package com.example.ApiRestStore.dto;

public class ProductDTO {
    
    private Long idProductDTO;
    private String name;
    private Double price;

    public ProductDTO() {
    }

    public ProductDTO(Long idProduct, String name, Double price) {
        this.idProductDTO = idProduct;
        this.name = name;
        this.price = price;
    }

    public Long getIdProductDTO() {
        return idProductDTO;
    }

    public void setIdProductDTO(Long idProductDTO) {
        this.idProductDTO = idProductDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "idProduct=" + idProductDTO + ", name=" + name + ", price=" + price + '}';
    }
}
