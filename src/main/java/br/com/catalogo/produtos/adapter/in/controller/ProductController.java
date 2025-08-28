package br.com.catalogo.produtos.adapter.in.controller;

import br.com.catalogo.produtos.adapter.in.request.ProductInsertRequest;
import br.com.catalogo.produtos.adapter.in.request.ProductRequest;
import br.com.catalogo.produtos.response.ProductResponse;
import br.com.catalogo.produtos.filter.ProductFilter;
import br.com.catalogo.produtos.core.usecase.ProductUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Produtos", description = "Endpoint de cadastro de produtos")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductUseCase service;

    public ProductController(ProductUseCase service) {
        this.service = service;
    }

    @Operation(summary = "Lista os produtos (paginado).")
    @GetMapping(path = "")
    public ResponseEntity<Page<ProductResponse>> findAll(
            @ParameterObject ProductFilter filter,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findAll(filter, pageable));
    }

    @Operation(summary = "Busca um produto pelo seu ID.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @Operation(summary = "Lista os produtos por nome (completo ou parte). A busca ignora letras maiúsculas/minúsculas e acentos.")
    @GetMapping(path = "/searchByName")
    public ResponseEntity<Page<ProductResponse>> findByName(
            @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findByName(name, pageable));
    }

    @Operation(summary = "Lista os produtos por categoria.")
    @GetMapping(path = "/category/{id}")
    public ResponseEntity<Page<ProductResponse>> findByCategoryId(
            @PathVariable Long id,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findByCategoryId(id, pageable));
    }

    @Operation(summary = "Lista os produtos por nome (completo ou parte) da categoria. A busca ignora letras maiúsculas/minúsculas e acentos.")
    @GetMapping(path = "/category/searchByName")
    public ResponseEntity<Page<ProductResponse>> findByCategoryName(
            @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findByCategoryName(name, pageable));
    }

    @Operation(summary = "Insere um produto.")
    @PostMapping(path = "")
    public ResponseEntity<ProductResponse> create(
            @RequestBody @Valid ProductInsertRequest dto,
            UriComponentsBuilder uriBuilder
    ) {
        ProductResponse response = this.service.create(dto);
        URI location = uriBuilder
                .path("/products/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Atualiza um produto.")
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid ProductRequest dto) {
        return ResponseEntity.ok(this.service.update(id, dto));
    }

    @Operation(summary = "Remove um produto.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProductResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.delete(id));
    }

    @Operation(summary = "Gera o código de barras pelo código do EAN")
    @GetMapping(value = "/barcode/{ean}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getBarcode(@PathVariable String ean) {
        return ResponseEntity.ok(this.service.generateBarcodeByEAN(ean));
    }

    @Operation(summary = "Gera o código de barras pelo ID do produto")
    @GetMapping(value = "/barcode/{id}/productId", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getBarcode(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.generateBarcodeByProductId(id));
    }

}
