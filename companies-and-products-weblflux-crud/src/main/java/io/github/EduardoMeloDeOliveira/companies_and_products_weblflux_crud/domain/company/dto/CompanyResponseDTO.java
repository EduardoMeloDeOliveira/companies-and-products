package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.dto;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.entity.Company;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto.ProductRequestDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto.ProductResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public record CompanyResponseDTO(
        String id,
        String name,
        String sector,
        List<ProductResponseDTO> products
) {

    public CompanyResponseDTO(Company company) {
        this(
                company.getId(),
                company.getName(),
                company.getSector(),
                company.getProducts()
                        .stream()
                        .map(ProductResponseDTO::new)
                        .collect(Collectors.toList()));
    }
}
