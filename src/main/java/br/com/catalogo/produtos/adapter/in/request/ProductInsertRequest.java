package br.com.catalogo.produtos.adapter.in.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInsertRequest {

    @NotBlank(message = "{product.name.blank}")
    @Size(max = 120, message = "{product.name.size}")
    String name;

    @NotBlank(message = "{product.description.blank}")
    @Size(max = 500, message = "{product.description.size}")
    String description;

    @NotNull(message = "{product.price.null}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{product.price.min}")
    BigDecimal price;

    @NotBlank(message = "{product.ean.blank}")
    @Size(min = 13, max = 13, message = "{product.ean.size}")
    String ean;

    @NotNull(message = "{product.category.null}")
    Long categoryId;

}
