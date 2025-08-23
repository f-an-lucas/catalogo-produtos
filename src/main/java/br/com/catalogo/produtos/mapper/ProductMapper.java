package br.com.catalogo.produtos.mapper;

import br.com.catalogo.produtos.dto.ProductInsertRequestDTO;
import br.com.catalogo.produtos.dto.ProductRequestDTO;
import br.com.catalogo.produtos.dto.ProductResponseDTO;
import br.com.catalogo.produtos.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductResponseDTO toDTO(Product entity);

    Product toEntity(ProductRequestDTO dto);
    Product toEntity(ProductInsertRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ProductRequestDTO dto, @MappingTarget Product product);

}
