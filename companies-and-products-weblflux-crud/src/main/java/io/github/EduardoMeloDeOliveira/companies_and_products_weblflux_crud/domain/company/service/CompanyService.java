package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.service;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.dto.CompanyRequestDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.dto.CompanyResponseDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.entity.Company;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Mono<CompanyResponseDTO> registerCompany(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company(companyRequestDTO);
        return companyRepository.save(company)
                .map(CompanyResponseDTO::new);
    }

    public Mono<CompanyResponseDTO> getCompanyById(String id) {
        return companyRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found")))
                .map(CompanyResponseDTO::new);
    }

    public Flux<CompanyResponseDTO> getAllCompanies() {
        return companyRepository.findAll()
                .map(CompanyResponseDTO::new);
    }

    public Mono<Void> deleteCompanyById(String id) {
        return companyRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found")))
                .then(companyRepository.deleteById(id));


    }

}
