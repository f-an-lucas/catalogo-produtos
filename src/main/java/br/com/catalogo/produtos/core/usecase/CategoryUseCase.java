package br.com.catalogo.produtos.core.usecase;

import br.com.catalogo.produtos.adapter.in.request.CategoryRequest;
import br.com.catalogo.produtos.response.CategoryResponse;
import br.com.catalogo.produtos.exception.ConflictException;
import br.com.catalogo.produtos.exception.NotFoundException;
import br.com.catalogo.produtos.adapter.out.repository.entity.Category;
import br.com.catalogo.produtos.filter.CategoryFilter;
import br.com.catalogo.produtos.mapper.CategoryMapper;
import br.com.catalogo.produtos.adapter.out.repository.CategoryRepository;
import br.com.catalogo.produtos.spec.CategorySpec;
import br.com.catalogo.produtos.util.TextNormalizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryUseCase {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;

    public CategoryUseCase(
            CategoryRepository repository,
            CategoryMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponse> findAll(CategoryFilter filter, Pageable pageable) {
        return this.repository.findAll(
                CategorySpec.withFilter(filter),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return this.mapper.toDTO(
                this.repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Categoria com ID '%s' não encontrada!".formatted(id)
                        ))
        );
    }

    @Transactional(readOnly = true)
    public Category findEntityById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Categoria com ID '%s' não encontrada!".formatted(id)
                ));
    }

    @Transactional(readOnly = true)
    public Page<CategoryResponse> findByName(String name, Pageable pageable) {
        return this.repository.findByNameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional
    public CategoryResponse create(CategoryRequest dto) {
        this.nameExists(dto.getName());
        Category category = this.mapper.toEntity(dto);
        category.setId(null);
        return this.mapper.toDTO(this.repository.save(category));
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryRequest dto) {
        Category category = this.findEntityById(id);
        this.nameExists(dto.getName());
        category.setName(dto.getName());
        return this.mapper.toDTO(this.repository.save(category));
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
