package br.com.catalogo.produtos.dto;

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
public class ProductRequestDTO {

    @Size(max = 120)
    String name;

    @Size(max = 500)
    String description;

    @DecimalMin(value = "0.0", inclusive = true)
    BigDecimal price;

    @Min(0)
    Long stock;

    Long categoryId;

}
