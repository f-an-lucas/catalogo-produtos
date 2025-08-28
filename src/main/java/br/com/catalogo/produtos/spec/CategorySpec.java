package br.com.catalogo.produtos.spec;

import br.com.catalogo.produtos.adapter.out.repository.entity.Category;
import br.com.catalogo.produtos.filter.CategoryFilter;
import br.com.catalogo.produtos.util.TextNormalizer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategorySpec {

    public static Specification<Category> withFilter(CategoryFilter filter) {

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(filter.getName())
                    .filter(StringUtils::hasText)
                    .map(p -> UriUtils.decode(p, "UTF-8").toLowerCase())
                    .map(TextNormalizer::normalizeText)
                    .ifPresent(p -> predicates.add(
                            builder.like(builder.lower(root.get("nameNormalized")), "%" + p + "%")
                    ));

            return builder.and(predicates.toArray(new Predicate[0]));
        };

    }

}
