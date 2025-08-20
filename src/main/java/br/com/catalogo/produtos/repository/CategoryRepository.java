package br.com.catalogo.produtos.repository;

import br.com.catalogo.produtos.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Page<Category> findByNameNormalizedContainingIgnoreCase(String nameNormalized, Pageable pageable);
    boolean existsByNameIgnoreCase(String name);

}
