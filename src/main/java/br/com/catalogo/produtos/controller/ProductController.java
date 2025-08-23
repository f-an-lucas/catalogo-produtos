package br.com.catalogo.produtos.controller;

import br.com.catalogo.produtos.dto.ProductInsertRequestDTO;
import br.com.catalogo.produtos.dto.ProductRequestDTO;
import br.com.catalogo.produtos.dto.ProductResponseDTO;
import br.com.catalogo.produtos.filter.ProductFilter;
import br.com.catalogo.produtos.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Produtos", description = "Endpoint de cadastro de produtos")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Lista os produtos (paginado).")
    @GetMapping(path = "")
    public ResponseEntity<Page<ProductResponseDTO>> findAll(
            @ParameterObject ProductFilter filter,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findAll(filter, pageable));
    }

    @Operation(summary = "Busca um produto pelo seu ID.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @Operation(summary = "Lista os produtos por nome (completo ou parte). A busca ignora letras maiúsculas/minúsculas e acentos.")
    @GetMapping(path = "/searchByName")
    public ResponseEntity<Page<ProductResponseDTO>> findByName(
            @ParameterObject @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findByName(name, pageable));
    }

    @Operation(summary = "Lista os produtos por categoria.")
    @GetMapping(path = "/category/{id}")
    public ResponseEntity<Page<ProductResponseDTO>> findByCategoryId(
            @ParameterObject @PathVariable Long id,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findByCategoryId(id, pageable));
    }

    @Operation(summary = "Lista os produtos por nome (completo ou parte) da categoria. A busca ignora letras maiúsculas/minúsculas e acentos.")
    @GetMapping(path = "/category/searchByName")
    public ResponseEntity<Page<ProductResponseDTO>> findByCategoryName(
            @ParameterObject @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findByCategoryName(name, pageable));
    }

    @Operation(summary = "Insere um produto.")
    @PostMapping(path = "")
    public ResponseEntity<ProductResponseDTO> create(
            @RequestBody @Validated ProductInsertRequestDTO dto,
            UriComponentsBuilder uriBuilder
    ) {
        ProductResponseDTO response = this.service.create(dto);
        URI location = uriBuilder
                .path("/products/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Atualiza um produto.")
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody @Validated ProductRequestDTO dto) {
        return ResponseEntity.ok(this.service.update(id, dto));
    }

    @Operation(summary = "Remove um produto.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.delete(id));
    }

}
