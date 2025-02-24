package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.entity;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.dto.CompanyRequestDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "company")
public class Company {

    @Id
    private String id;

    private String name;

    private String sector;

    private List<Product> products = new ArrayList<>();


    public Company(CompanyRequestDTO requestDTO) {
        this.name = requestDTO.name();
        this.sector = requestDTO.sector();
    }
}
