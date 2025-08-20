package br.com.catalogo.produtos.controller;

import br.com.catalogo.produtos.dto.ProductRequest;
import br.com.catalogo.produtos.dto.ProductResponse;
import br.com.catalogo.produtos.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    @Operation(summary = "Cria um produto")
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ProductResponse create(@RequestBody @Validated ProductRequest req) {
//        return service.create(req);
//    }

    @Operation(summary = "Lista produtos (paginado)")
    @GetMapping
    public Page<ProductResponse> list(@ParameterObject Pageable pageable) {
        return service.findAll(pageable);
    }

    @Operation(summary = "Busca produto por id")
    @GetMapping("{id}")
    public ProductResponse get(@PathVariable Long id) {
        return service.get(id);
    }

//    @Operation(summary = "Atualiza um produto")
//    @PutMapping("{id}")
//    public ProductResponse update(@PathVariable Long id, @RequestBody @Validated ProductRequest req) {
//        return service.update(id, req);
//    }

    @Operation(summary = "Remove um produto")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
