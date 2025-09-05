package ru.kashtanov.product.internal.service.impl.service;

import org.osgi.service.component.annotations.Component;
import ru.kashtanov.product.service.api.dto.ProductDto;
import ru.kashtanov.product.service.api.dto.ProductRequestDto;
import ru.kashtanov.product.service.api.service.ProductService;

import java.util.List;

@Component(service = ProductService.class)
public class ProductServiceImpl implements ProductService {

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {

    }

    @Override
    public ProductDto getProductById(long id) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return List.of();
    }

    @Override
    public void updateProduct(ProductRequestDto productRequestDto) {

    }

    @Override
    public void deleteProduct(long id) {

    }
}