package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.repository;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.entity.Company;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {
}
