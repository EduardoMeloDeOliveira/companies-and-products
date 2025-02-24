package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.service;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.entity.Company;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.repository.CompanyRepository;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto.ProductRequestDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto.ProductResponseDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CompanyRepository companyRepository;

    public Mono<ProductResponseDTO> addProductToCompany(String companyId, ProductRequestDTO productDTO) {
        return companyRepository.findById(companyId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found")))
                .flatMap(company -> {
                    Product product = new Product(productDTO);
                    company.getProducts().add(product);
                    return companyRepository.save(company)
                            .map(savedCompany -> {
                                Product savedProduct = savedCompany.getProducts().get(savedCompany.getProducts().size() - 1);
                                return new ProductResponseDTO(savedProduct);
                            });
                });
    }


    public Mono<ProductResponseDTO> getProductById(String companyId, String productId) {

        return companyRepository.findById(companyId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found")))
                .flatMapMany(company -> Flux.fromIterable(company.getProducts()))
                .filter(product -> product.getId().equals(productId))
                .singleOrEmpty()
                .map(ProductResponseDTO::new);
    }


    public Flux<ProductResponseDTO> getAllProducts(String companyId) {
        return companyRepository.findById(companyId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found")))
                .flatMapMany(company -> Flux.fromIterable(company.getProducts()))
                .map(ProductResponseDTO::new);
    }

    public Mono<Void> deleteProductById(String companyId, String productId) {
        return companyRepository.findById(companyId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found")))
                .flatMap(company -> accertProductIsRemoved(company, productId)
                );
    }

    private Mono<Void> accertProductIsRemoved(Company company, String productId) {
        boolean productIsRemoved = company.getProducts().removeIf(product -> product.getId().equals(productId));
        if (!productIsRemoved) {
            return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        }
        return companyRepository.save(company).then();
    }
}
