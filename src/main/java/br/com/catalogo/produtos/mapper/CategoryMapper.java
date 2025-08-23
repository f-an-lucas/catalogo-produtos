package br.com.catalogo.produtos.mapper;

import br.com.catalogo.produtos.dto.CategoryRequestDTO;
import br.com.catalogo.produtos.dto.CategoryResponseDTO;
import br.com.catalogo.produtos.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryResponseDTO toDTO(Category entity);

    Category toEntity(CategoryRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CategoryRequestDTO dto, @MappingTarget Category category);

}
