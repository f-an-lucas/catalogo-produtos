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
public class ProductRequest {

    @NotBlank
    @Size(max = 120)
    String name;

    @Size(max = 500)
    String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    BigDecimal price;

    @NotNull
    @Min(0)
    Integer stock;

    @NotNull
    Long categoryId;

}
