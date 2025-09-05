package ru.kashtanov.product.service.api.dto;

public record ProductRequestDto (long productId,
                                 String productName,
                                 String productDescription){}
