package br.com.catalogo.produtos.controller;

import br.com.catalogo.produtos.dto.CategoryRequestDTO;
import br.com.catalogo.produtos.dto.CategoryResponseDTO;
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

    @Operation(summary = "Lista as categorias (paginado).")
    @GetMapping(path = "")
    public Page<CategoryResponseDTO> findAll(
            @ParameterObject CategoryFilter filter,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return this.service.findAll(filter, pageable);
    }

    @Operation(summary = "Busca uma categoria pelo seu ID.")
    @GetMapping(path = "/{id}")
    public CategoryResponseDTO findById(@PathVariable Long id) {
        return this.service.findById(id);
    }

    @Operation(summary = "Lista as categorias por nome (completo ou parte). A busca ignora letras maiúsculas/minúsculas e acentos.")
    @GetMapping(path = "/searchByName")
    public Page<CategoryResponseDTO> findByName(
            @ParameterObject @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable
    ) {
        return this.service.findByName(name, pageable);
    }

    @Operation(summary = "Cria uma categoria")
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO create(@RequestBody @Validated CategoryRequestDTO dto) {
        return this.service.create(dto);
    }

    @Operation(summary = "Atualiza uma categoria")
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDTO update(@PathVariable Long id, @RequestBody @Validated CategoryRequestDTO dto) {
        return this.service.update(id, dto);
    }

    @Operation(summary = "Remove uma categoria")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDTO delete(@PathVariable Long id) {
        return this.service.delete(id);
    }

}
