package br.com.catalogo.produtos.service;

import br.com.catalogo.produtos.config.MapperUtils;
import br.com.catalogo.produtos.dto.CategoryRequest;
import br.com.catalogo.produtos.dto.CategoryResponse;
import br.com.catalogo.produtos.dto.ProductRequest;
import br.com.catalogo.produtos.entity.Category;
import br.com.catalogo.produtos.entity.Product;
import br.com.catalogo.produtos.exception.ConflictException;
import br.com.catalogo.produtos.exception.NotFoundException;
import br.com.catalogo.produtos.dto.ProductResponse;
import br.com.catalogo.produtos.filter.ProductFilter;
import br.com.catalogo.produtos.repository.ProductRepository;
import br.com.catalogo.produtos.spec.ProductSpec;
import br.com.catalogo.produtos.util.TextNormalizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final MapperUtils mapper;
    private final ProductRepository repository;

    public ProductService(
            MapperUtils mapper,
            ProductRepository repository
    ) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(ProductFilter filter, Pageable pageable) {
        return this.repository.findAll(
                ProductSpec.withFilter(filter),
                pageable
        ).map(product -> this.mapper.toDTO(product, ProductResponse.class));
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return this.mapper.toDTO(
                this.repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Produto com ID '%s' não encontrado!".formatted(id)
                        )),
                ProductResponse.class
        );
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findByName(String name, Pageable pageable) {
        return this.repository.findByNameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(product -> this.mapper.toDTO(product, ProductResponse.class));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findByCategoryId(Long id, Pageable pageable) {
        return this.repository.findByCategory_Id(
                id,
                pageable
        ).map(product -> this.mapper.toDTO(product, ProductResponse.class));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findByCategoryName(String name, Pageable pageable) {
        return this.repository.findByCategory_NameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(product -> this.mapper.toDTO(product, ProductResponse.class));
    }

    @Transactional
    public ProductResponse create(ProductRequest dto) {
        this.nameExists(dto.getName());
        Product product = this.mapper.toEntity(dto, Product.class);
        product.setId(null);
        return this.mapper.toDTO(this.repository.save(product), ProductResponse.class);
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest dto) {
        Product product = this.mapper.toEntity(this.findById(id),  Product.class);
        this.nameExists(product.getName(), dto.getName());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return this.mapper.toDTO(this.repository.save(product), ProductResponse.class);
    }

    @Transactional
    public ProductResponse delete(Long id) {
        ProductResponse dto = this.findById(id);
        this.repository.deleteById(id);
        return dto;
    }

    private void nameExists(String name) {
        if (this.repository.existsByNameIgnoreCase(name)) {
            throw new ConflictException("Produto '%s' já está cadastrado!".formatted(name));
        }
    }

    private void nameExists(String originalName, String newName) {
        if (originalName.equals(newName)) return;
        if (this.repository.existsByNameIgnoreCase(newName)) {
            throw new ConflictException("Produto '%s' já está cadastrado!".formatted(newName));
        }
    }

}
