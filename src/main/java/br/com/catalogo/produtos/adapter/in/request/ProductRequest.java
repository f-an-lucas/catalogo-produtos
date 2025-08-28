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
public class ProductRequest {

    @Size(max = 120, message = "{product.name.size}")
    String name;

    @Size(max = 500, message = "{product.description.size}")
    String description;

    @DecimalMin(value = "0.0", inclusive = true, message = "{product.price.min}")
    BigDecimal price;

    @Size(min = 13, max = 13, message = "{product.ean.size}")
    String ean;

    Long categoryId;

}
