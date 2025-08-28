package br.com.catalogo.produtos.mapper;

import br.com.catalogo.produtos.adapter.in.request.ProductInsertRequest;
import br.com.catalogo.produtos.adapter.in.request.ProductRequest;
import br.com.catalogo.produtos.response.ProductResponse;
import br.com.catalogo.produtos.adapter.out.repository.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductResponse toDTO(Product entity);

    Product toEntity(ProductRequest dto);
    Product toEntity(ProductInsertRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ProductRequest dto, @MappingTarget Product product);

}
