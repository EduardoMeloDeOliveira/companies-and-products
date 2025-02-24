package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.entity.Product;

public record ProductResponseDTO(
        String productId,
        String productName,
        Double productPrice
) {

    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}
