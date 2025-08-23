package br.com.catalogo.produtos.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Schema(name = "ProductFilter", description = "Filtro para pesquisa de produtos.")
public class ProductFilter {

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "Nome completo ou parcial do produto. Operação de similaridade (like). Case-insensitive."
    )
    private String name;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "Nome completo ou parcial da categoria do produto. Operação de similaridade (like). Case-insensitive."
    )
    private String categoryName;

}
