package br.com.catalogo.produtos.service;

import br.com.catalogo.produtos.dto.ProductInsertRequestDTO;
import br.com.catalogo.produtos.dto.ProductRequestDTO;
import br.com.catalogo.produtos.dto.ProductResponseDTO;
import br.com.catalogo.produtos.entity.Product;
import br.com.catalogo.produtos.exception.ConflictException;
import br.com.catalogo.produtos.exception.NotFoundException;
import br.com.catalogo.produtos.filter.ProductFilter;
import br.com.catalogo.produtos.mapper.ProductMapper;
import br.com.catalogo.produtos.repository.ProductRepository;
import br.com.catalogo.produtos.spec.ProductSpec;
import br.com.catalogo.produtos.util.TextNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper mapper;
    private final ProductRepository repository;
    private final CategoryService categoryService;

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAll(ProductFilter filter, Pageable pageable) {
        return this.repository.findAll(
                ProductSpec.withFilter(filter),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        return this.mapper.toDTO(
                this.repository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Produto com ID '%s' não encontrado!".formatted(id)
                        )));
    }

    @Transactional(readOnly = true)
    public Product findEntityById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Produto com ID '%s' não encontrado!".formatted(id)
                ));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findByName(String name, Pageable pageable) {
        return this.repository.findByNameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findByCategoryId(Long id, Pageable pageable) {
        return this.repository.findByCategory_Id(
                id,
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findByCategoryName(String name, Pageable pageable) {
        return this.repository.findByCategory_NameNormalizedContainingIgnoreCase(
                TextNormalizer.normalizeText(name),
                pageable
        ).map(this.mapper::toDTO);
    }

    @Transactional
    public ProductResponseDTO create(ProductInsertRequestDTO dto) {
        this.nameExists(dto.getName());
        Product product = this.mapper.toEntity(dto);
        product.setCategory(this.categoryService.findEntityById(dto.getCategoryId()));
        return this.mapper.toDTO(this.repository.save(product));
    }

    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product product = this.findEntityById(id);

        this.nameExists(product.getName(), dto.getName());

        this.mapper.updateFromDto(dto, product);

        if (dto.getCategoryId() != null) {
            product.setCategory(this.categoryService.findEntityById(dto.getCategoryId()));
        }

        return this.mapper.toDTO(this.repository.save(product));
    }

    @Transactional
    public ProductResponseDTO delete(Long id) {
        ProductResponseDTO dto = this.findById(id);
        this.repository.deleteById(id);
        return dto;
    }

    @Transactional
    public ProductResponseDTO addToStock(Long id, Long quantity) {
        this.checkQuantity(quantity);
        Product product = this.findEntityById(id);
        product.setStock(product.getStock() + quantity);
        return this.mapper.toDTO(this.repository.save(product));
    }

    @Transactional
    public ProductResponseDTO removeFromStock(Long id, Long quantity) {
        this.checkQuantity(quantity);
        Product product = this.findEntityById(id);

        if (product.getStock() < quantity) {
            throw new ConflictException("Estoque insuficiente para o produto '%s'".formatted(product.getName()));
        }

        product.setStock(product.getStock() - quantity);
        return this.mapper.toDTO(this.repository.save(product));
    }

    private void checkQuantity(Long quantity) {
        if (quantity == null || quantity <= 0L) {
            throw new IllegalArgumentException("Quantidade deve ser positiva!");
        }
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
