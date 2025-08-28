package br.com.catalogo.produtos.adapter.out.repository.entity;

import br.com.catalogo.produtos.util.TextNormalizer;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(name = "uk_category_name", columnNames = "name"))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(name = "name_normalized", nullable = false, length = 80)
    private String nameNormalized;

    @PrePersist
    @PreUpdate
    private void prePersist() {
        this.nameNormalized = TextNormalizer.normalizeText(this.name);
    }

}
