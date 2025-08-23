package br.com.catalogo.produtos.service;

import br.com.catalogo.produtos.dto.CategoryRequestDTO;
import br.com.catalogo.produtos.dto.CategoryResponseDTO;
import br.com.catalogo.produtos.exception.ConflictException;
import br.com.catalogo.produtos.exception.NotFoundException;
import br.com.catalogo.produtos.entity.Category;
import br.com.catalogo.produtos.filter.CategoryFilter;
import br.com.catalogo.produtos.mapper.CategoryMapper;
import br.com.catalogo.produtos.repository.CategoryRepository;
import br.com.catalogo.produtos.spec.CategorySpec;
import br.com.catalogo.produtos.util.TextNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryResponseDTO> findAll(CategoryFilter filter, Pageable pageable) {
        return this.repository.findAll(
                CategorySpec.withFilter(filter),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long id) {
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
    public Page<CategoryResponseDTO> findByName(String name, Pageable pageable) {
        return this.repository.findByNameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        this.nameExists(dto.getName());
        Category category = this.mapper.toEntity(dto);
        category.setId(null);
        return this.mapper.toDTO(this.repository.save(category));
    }

    @Transactional
    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
        Category category = this.findEntityById(id);
        this.nameExists(dto.getName());
        category.setName(dto.getName());
        return this.mapper.toDTO(this.repository.save(category));
    }

    @Transactional
    public CategoryResponseDTO delete(Long id) {
        CategoryResponseDTO dto = this.findById(id);
        this.repository.deleteById(id);
        return dto;
    }

    private void nameExists(String name) {
        if (this.repository.existsByNameIgnoreCase(name)) {
            throw new ConflictException("Categoria '%s' já está cadastrada!".formatted(name));
        }
    }

}
