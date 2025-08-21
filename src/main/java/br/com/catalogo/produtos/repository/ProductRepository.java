package br.com.catalogo.produtos.repository;

import br.com.catalogo.produtos.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Page<Product> findByNameNormalizedContainingIgnoreCase(String nameNormalized, Pageable pageable);
    Page<Product> findByCategory_NameNormalizedContainingIgnoreCase(String nameNormalized, Pageable pageable);
    Page<Product> findByCategory_Id(Long id, Pageable pageable);
    boolean existsByNameIgnoreCase(String name);


}
