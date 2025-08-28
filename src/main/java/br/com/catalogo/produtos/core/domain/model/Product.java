package br.com.catalogo.produtos.core.domain.model;

import br.com.catalogo.produtos.response.CategoryResponse;

import java.math.BigDecimal;

public class Product {

    //    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String ean;
    private CategoryResponse category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }


}
