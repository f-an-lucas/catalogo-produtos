package br.com.catalogo.produtos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    Long id;
    String name;
    String description;
    BigDecimal price;
    Long stock;
    CategoryResponseDTO category;

}
