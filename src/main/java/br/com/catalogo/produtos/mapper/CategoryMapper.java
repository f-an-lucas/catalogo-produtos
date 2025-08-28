package br.com.catalogo.produtos.mapper;

import br.com.catalogo.produtos.adapter.in.request.CategoryRequest;
import br.com.catalogo.produtos.response.CategoryResponse;
import br.com.catalogo.produtos.adapter.out.repository.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryResponse toDTO(Category entity);

    Category toEntity(CategoryRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CategoryRequest dto, @MappingTarget Category category);

}
