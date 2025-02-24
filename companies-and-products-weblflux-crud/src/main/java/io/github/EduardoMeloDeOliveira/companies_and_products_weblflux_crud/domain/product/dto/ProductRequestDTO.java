package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.entity.Product;

public record ProductRequestDTO(
        String name,
        Double price
) {

    public ProductRequestDTO(Product product){
        this(product.getName(), product.getPrice());
    }


}
