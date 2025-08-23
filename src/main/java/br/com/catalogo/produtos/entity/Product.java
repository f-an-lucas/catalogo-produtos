package br.com.catalogo.produtos.entity;

import br.com.catalogo.produtos.util.TextNormalizer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(name = "name_normalized", nullable = false, length = 120)
    private String nameNormalized;

    @Column(length = 500)
    private String description;

    @Column(name = "description_normalized", length = 500)
    private String descriptionNormalized;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Long stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.normalize();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
        this.normalize();
    }

    private void normalize() {
        this.nameNormalized = TextNormalizer.normalizeText(this.name);
        this.descriptionNormalized = TextNormalizer.normalizeText(this.description);
    }

}
