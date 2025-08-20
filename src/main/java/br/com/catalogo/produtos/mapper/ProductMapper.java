package br.com.catalogo.produtos.mapper;

import br.com.catalogo.produtos.dto.CategoryResponse;
import br.com.catalogo.produtos.dto.ProductRequest;
import br.com.catalogo.produtos.dto.ProductResponse;
import br.com.catalogo.produtos.entity.Category;
import br.com.catalogo.produtos.entity.Product;

public class ProductMapper {

    public static ProductResponse toResponse(Product p) {
        CategoryResponse c = p.getCategory() == null
                ? null
                : new CategoryResponse(p.getCategory().getId(), p.getCategory().getName());
        return new ProductResponse(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getStock(), c);
    }

//    public static Product toEntity(ProductRequest req, Category category) {
//        return Product.builder()
//                .name(req.name())
//                .description(req.description())
//                .price(req.price())
//                .stock(req.stock())
//                .category(category)
//                .build();
//    }
//
//    public static void update(Product p, ProductRequest req, Category category) {
//        p.setName(req.name());
//        p.setDescription(req.description());
//        p.setPrice(req.price());
//        p.setStock(req.stock());
//        p.setCategory(category);
//    }
}
