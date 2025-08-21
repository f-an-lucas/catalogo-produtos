package br.com.catalogo.produtos.controller;

import br.com.catalogo.produtos.dto.CategoryRequest;
import br.com.catalogo.produtos.dto.CategoryResponse;
import br.com.catalogo.produtos.dto.ProductRequest;
import br.com.catalogo.produtos.dto.ProductResponse;
import br.com.catalogo.produtos.filter.ProductFilter;
import br.com.catalogo.produtos.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Produtos")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Lista os produtos (paginado)")
    @GetMapping(path = "")
    public Page<ProductResponse> findAll(
            @ParameterObject ProductFilter filter,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return this.service.findAll(filter, pageable);
    }

    @GetMapping(path = "/{id}")
    public ProductResponse findById(@PathVariable(name = "id") Long id) {
        return this.service.findById(id);
    }

    @GetMapping(path = "/searchByName")
    public Page<ProductResponse> findByName(
            @ParameterObject @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return this.service.findByName(name, pageable);
    }

    @GetMapping(path = "/category/{id}")
    public Page<ProductResponse> findByCategoryId(
            @PathVariable @RequestParam Long id,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return this.service.findByCategoryId(id, pageable);
    }

    @GetMapping(path = "/category/searchByName")
    public Page<ProductResponse> findByCategoryName(
            @ParameterObject @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return this.service.findByCategoryName(name, pageable);
    }

    @Operation(summary = "Insere um produto")
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Validated ProductRequest dto) {
        return this.service.create(dto);
    }

    @Operation(summary = "Atualiza um produto")
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse update(@PathVariable Long id, @RequestBody @Validated ProductRequest dto) {
        return this.service.update(id, dto);
    }

    @Operation(summary = "Remove um produto")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

}
