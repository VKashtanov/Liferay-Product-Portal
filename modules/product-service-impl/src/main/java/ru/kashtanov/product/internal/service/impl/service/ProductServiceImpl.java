package ru.kashtanov.product.internal.service.impl.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;
import ru.kashtanov.product.internal.service.impl.model.Product;
import ru.kashtanov.product.internal.service.impl.util.ProductDBUtil;
import ru.kashtanov.product.service.api.dto.ProductDto;
import ru.kashtanov.product.service.api.dto.ProductRequestDto;
import ru.kashtanov.product.service.api.service.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component(service = ProductService.class)
public class ProductServiceImpl implements ProductService {

    @Activate
    public void activate() {
        try {
            // Create table on startup
            ProductDBUtil.createTable();
            System.out.println("Product table created/verified successfully");
        } catch (SQLException e) {
            System.err.println("Error creating product table: " + e.getMessage());
        }
    }

    @Override
    public void createProduct(ProductRequestDto productRequestDto) {
        try {
            ProductDBUtil.createProduct(
                    productRequestDto.productName(),
                    productRequestDto.productDescription()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error creating product", e);
        }
    }

    @Override
    public ProductDto getProductById(long id) {
        try {
            Product product = ProductDBUtil.getProductById(id);
            if (product != null) {
                return new ProductDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductDescription()
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting product by id: " + id, e);
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            List<Product> products = ProductDBUtil.getAllProducts();
            return products.stream()
                    .map(product -> new ProductDto(
                            product.getProductId(),
                            product.getProductName(),
                            product.getProductDescription()
                    ))
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("Error getting all products", e);
        }
    }

    @Override
    public void updateProduct(ProductRequestDto productRequestDto) {
        try {
            ProductDBUtil.updateProduct(
                    productRequestDto.productId(),
                    productRequestDto.productName(),
                    productRequestDto.productDescription()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product", e);
        }
    }

    @Override
    public void deleteProduct(long id) {
        try {
            ProductDBUtil.deleteProduct(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product", e);
        }
    }
}