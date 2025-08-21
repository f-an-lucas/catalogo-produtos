package br.com.catalogo.produtos.service;

import br.com.catalogo.produtos.config.MapperUtils;
import br.com.catalogo.produtos.dto.CategoryRequest;
import br.com.catalogo.produtos.dto.CategoryResponse;
import br.com.catalogo.produtos.exception.ConflictException;
import br.com.catalogo.produtos.exception.NotFoundException;
import br.com.catalogo.produtos.entity.Category;
import br.com.catalogo.produtos.filter.CategoryFilter;
import br.com.catalogo.produtos.repository.CategoryRepository;
import br.com.catalogo.produtos.spec.CategorySpec;
import br.com.catalogo.produtos.util.TextNormalizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final MapperUtils mapper;
    private final CategoryRepository repository;

    public CategoryService(
            MapperUtils mapper,
            CategoryRepository repository
    ) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponse> findAll(CategoryFilter filter, Pageable pageable) {
        return this.repository.findAll(
                CategorySpec.withFilter(filter),
                pageable
        ).map(category -> this.mapper.toDTO(category, CategoryResponse.class));
    }

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return this.mapper.toDTO(
                this.repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Categoria com ID '%s' não encontrada!".formatted(id)
                        )),
                CategoryResponse.class
        );
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponse> findByName(String name, Pageable pageable) {
        return this.repository.findByNameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(category -> this.mapper.toDTO(category, CategoryResponse.class));
    }

    @Transactional
    public CategoryResponse create(CategoryRequest dto) {
        this.nameExists(dto.getName());
        Category category = this.mapper.toEntity(dto, Category.class);
        category.setId(null);
        return this.mapper.toDTO(this.repository.save(category), CategoryResponse.class);
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryRequest dto) {
        Category category = this.mapper.toEntity(this.findById(id),  Category.class);
        this.nameExists(dto.getName());
        category.setName(dto.getName());
        return this.mapper.toDTO(this.repository.save(category), CategoryResponse.class);
    }

    @Transactional
    public CategoryResponse delete(Long id) {
        CategoryResponse dto = this.findById(id);
        this.repository.deleteById(id);
        return dto;
    }

    private void nameExists(String name) {
        if (this.repository.existsByNameIgnoreCase(name)) {
            throw new ConflictException("Categoria '%s' já está cadastrada!".formatted(name));
        }
    }

}
