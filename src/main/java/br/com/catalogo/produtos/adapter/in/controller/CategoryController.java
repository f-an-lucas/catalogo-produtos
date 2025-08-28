package br.com.catalogo.produtos.adapter.in.controller;

import br.com.catalogo.produtos.adapter.in.request.CategoryRequest;
import br.com.catalogo.produtos.response.CategoryResponse;
import br.com.catalogo.produtos.filter.CategoryFilter;
import br.com.catalogo.produtos.core.usecase.CategoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Categorias", description = "Endpoint de cadastro de categorias")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryUseCase service;

    public CategoryController(CategoryUseCase service) {
        this.service = service;
    }

    @Operation(summary = "Lista as categorias (paginado).")
    @GetMapping(path = "")
    public ResponseEntity<Page<CategoryResponse>> findAll(
            @ParameterObject CategoryFilter filter,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findAll(filter, pageable));
    }

    @Operation(summary = "Busca uma categoria pelo seu ID.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @Operation(summary = "Lista as categorias por nome (completo ou parte). A busca ignora letras maiúsculas/minúsculas e acentos.")
    @GetMapping(path = "/searchByName")
    public ResponseEntity<Page<CategoryResponse>> findByName(
            @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(this.service.findByName(name, pageable));
    }

    @Operation(summary = "Cria uma categoria")
    @PostMapping(path = "")
    public ResponseEntity<CategoryResponse> create(
            @RequestBody @Valid CategoryRequest dto,
            UriComponentsBuilder uriBuilder
    ) {
        CategoryResponse response = this.service.create(dto);
        URI location = uriBuilder
                .path("/categories/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Atualiza uma categoria")
    @PutMapping(path = "/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid CategoryRequest dto) {
        return ResponseEntity.ok(this.service.update(id, dto));
    }

    @Operation(summary = "Remove uma categoria")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CategoryResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.delete(id));
    }

}
