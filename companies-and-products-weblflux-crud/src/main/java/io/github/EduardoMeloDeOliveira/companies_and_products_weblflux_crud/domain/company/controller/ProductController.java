package io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.company.controller;

import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto.ProductRequestDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.dto.ProductResponseDTO;
import io.github.EduardoMeloDeOliveira.companies_and_products_weblflux_crud.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/{companyId}")
    public Mono<ProductResponseDTO> createProduct(@PathVariable String companyId, @RequestBody ProductRequestDTO productRequestDTO) {
        return productService.addProductToCompany(companyId, productRequestDTO);
    }

    @GetMapping("/{companyId}/{productId}")
    public Mono<ProductResponseDTO> getProductById(@PathVariable String companyId, @PathVariable String productId) {
        return productService.getProductById(companyId, productId);
    }

    @GetMapping("/{companyId}")
    public Flux<ProductResponseDTO> getAllProducts(@PathVariable String companyId) {
        return productService.getAllProducts(companyId);
    }

    @DeleteMapping("/{companyId}/{productId}")
    public Mono<Void> deleteProductById(@PathVariable String companyId, @PathVariable String productId) {
        return productService.deleteProductById(companyId, productId);
    }
}
