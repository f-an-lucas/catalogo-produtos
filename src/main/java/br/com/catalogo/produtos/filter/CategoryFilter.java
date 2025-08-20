package br.com.catalogo.produtos.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "CategoryFilter", description = "Filtro para pesquisa de categorias de produtos.")
public class CategoryFilter {

    @Schema(example = "Eletrônicos", requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Nome completo ou parcial da categoria.\nOperação de similaridade (like).\nCase-insensitive.")
    private String name;

}
