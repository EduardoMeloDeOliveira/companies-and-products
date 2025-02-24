package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequestDTO(
        @NotBlank
        String name,
        @NotBlank
        String sector
) {
}
