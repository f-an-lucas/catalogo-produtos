package br.com.catalogo.produtos.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Schema(name = "CategoryFilter", description = "Filtro para pesquisa de categorias de produtos.")
public class CategoryFilter {

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "Nome completo ou parcial da categoria. Operação de similaridade (like). Case-insensitive."
    )
    private String name;

}
