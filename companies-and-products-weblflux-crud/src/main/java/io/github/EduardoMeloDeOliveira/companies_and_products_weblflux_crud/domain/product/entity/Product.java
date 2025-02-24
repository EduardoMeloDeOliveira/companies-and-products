package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.entity;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private Double price;


    public Product(ProductRequestDTO productRequestDTO) {
        this.id = UUID.randomUUID().toString();
        this.name = productRequestDTO.name();
        this.price = productRequestDTO.price();
    }

}
