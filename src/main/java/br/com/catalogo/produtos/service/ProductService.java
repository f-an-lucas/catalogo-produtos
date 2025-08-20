package br.com.catalogo.produtos.service;

import br.com.catalogo.produtos.config.MapperUtils;
import br.com.catalogo.produtos.exception.NotFoundException;
import br.com.catalogo.produtos.dto.ProductRequest;
import br.com.catalogo.produtos.dto.ProductResponse;
import br.com.catalogo.produtos.mapper.ProductMapper;
import br.com.catalogo.produtos.entity.Category;
import br.com.catalogo.produtos.entity.Product;
import br.com.catalogo.produtos.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final MapperUtils mapper;

    public ProductService(ProductRepository repository, CategoryService categoryService, MapperUtils mapper) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

//    @Transactional
//    public ProductResponse create(ProductRequest req) {
//        Category category = categoryService.getByIdOrThrow(req.categoryId());
//        Product product = repository.save(mapper.toEntity(req, Product.class));
//        return mapper.toDTO(product, ProductResponse.class);
//    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(Pageable pageable) {
        return this.repository.findAll(pageable).map(p -> mapper.toDTO(p, ProductResponse.class));
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado: " + id));
    }

    @Transactional(readOnly = true)
    public ProductResponse get(Long id) {
        return ProductMapper.toResponse(findById(id));
    }

//    @Transactional
//    public ProductResponse update(Long id, ProductRequest req) {
//        Product p = getByIdOrThrow(id);
//        Category category = categoryService.getByIdOrThrow(req.categoryId());
//        ProductMapper.update(p, req, category);
//        return ProductMapper.toResponse(p);
//    }

    @Transactional
    public void delete(Long id) {
        Product p = findById(id);
        repository.delete(p);
    }
}
