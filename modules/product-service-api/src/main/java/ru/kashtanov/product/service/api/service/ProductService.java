package ru.kashtanov.product.service.api.service;

import ru.kashtanov.product.service.api.dto.ProductDto;
import ru.kashtanov.product.service.api.dto.ProductRequestDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequestDto productRequestDto);
    ProductDto getProductById(long id);
    List<ProductDto> getAllProducts();
    void updateProduct(ProductRequestDto productRequestDto);
    void deleteProduct(long id);
}
