package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.controller;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.dto.CompanyRequestDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.dto.CompanyResponseDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CompanyResponseDTO> registerCompany
            (@RequestBody @Valid CompanyRequestDTO companyRequestDTO) {
        return companyService.registerCompany(companyRequestDTO);
    }

    @GetMapping("/{companyId}")
    public Mono<CompanyResponseDTO> getCompanyById(@PathVariable String companyId) {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping
    public Flux<CompanyResponseDTO> getAllCompanies() {
        return companyService.getAllCompanies();
    }


    @DeleteMapping("/{companyId}")
    public Mono<Void> deleteCompanyById(@PathVariable String companyId) {
        return companyService.deleteCompanyById(companyId);
    }

}
