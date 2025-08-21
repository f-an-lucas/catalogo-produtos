package br.com.catalogo.produtos.spec;

import br.com.catalogo.produtos.entity.Product;
import br.com.catalogo.produtos.filter.ProductFilter;
import br.com.catalogo.produtos.util.TextNormalizer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductSpec {

    public static Specification<Product> withFilter(ProductFilter filter) {

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(filter.getName())
                    .filter(StringUtils::hasText)
                    .map(productName -> UriUtils.decode(productName, "UTF-8").toLowerCase())
                    .map(TextNormalizer::normalizeText)
                    .ifPresent(productName -> predicates.add(
                            builder.like(builder.lower(root.get("nameNormalized")), "%" + productName + "%")
                    ));

            Optional.ofNullable(filter.getCategoryName())
                    .filter(StringUtils::hasText)
                    .map(categoryName -> UriUtils.decode(categoryName, "UTF-8").toLowerCase())
                    .map(TextNormalizer::normalizeText)
                    .ifPresent(categoryName -> predicates.add(
                            builder.like(builder.lower(root.get("category").get("nameNormalized")), "%" + categoryName + "%")
                    ));

            return builder.and(predicates.toArray(new Predicate[0]));
        };

    }

}
