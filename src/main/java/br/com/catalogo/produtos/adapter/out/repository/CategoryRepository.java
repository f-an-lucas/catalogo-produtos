package br.com.catalogo.produtos.adapter.out.repository;

import br.com.catalogo.produtos.adapter.out.repository.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Page<Category> findByNameNormalizedContainingIgnoreCase(String nameNormalized, Pageable pageable);
    boolean existsByNameIgnoreCase(String name);

}
