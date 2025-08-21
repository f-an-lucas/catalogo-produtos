package br.com.catalogo.produtos.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Schema(name = "ProductFilter", description = "Filtro para pesquisa de produtos.")
public class ProductFilter {

    @Schema(example = "Calça", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Nome completo ou parcial do produto.\nOperação de similaridade (like).\nCase-insensitive.")
    private String name;

    @Schema(example = "Eletrônico", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Nome completo ou parcial da categoria do produto.\nOperação de similaridade (like).\nCase-insensitive.")
    private String categoryName;

}
