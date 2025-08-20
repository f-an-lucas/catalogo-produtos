package br.com.catalogo.produtos.controller;

import br.com.catalogo.produtos.dto.CategoryRequest;
import br.com.catalogo.produtos.dto.CategoryResponse;
import br.com.catalogo.produtos.filter.CategoryFilter;
import br.com.catalogo.produtos.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Categorias")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @Operation(summary = "Lista as categorias (paginado)")
    @GetMapping(path = "")
    public Page<CategoryResponse> findAll(
            @ParameterObject CategoryFilter filter,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return this.service.findAll(filter, pageable);
    }

    @GetMapping(path = "/{id}")
    public CategoryResponse findById(@PathVariable(name = "id") Long id) {
        return this.service.findById(id);
    }

    @GetMapping(path = "/searchByName")
    public Page<CategoryResponse> findByName(
            @ParameterObject @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return this.service.findByName(name, pageable);
    }

    @Operation(summary = "Cria uma categoria")
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@RequestBody @Validated CategoryRequest dto) {
        return this.service.create(dto);
    }

    @Operation(summary = "Atualiza uma categoria")
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse update(@PathVariable Long id, @RequestBody @Validated CategoryRequest dto) {
        return this.service.update(id, dto);
    }

    @Operation(summary = "Remove uma categoria")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

}
