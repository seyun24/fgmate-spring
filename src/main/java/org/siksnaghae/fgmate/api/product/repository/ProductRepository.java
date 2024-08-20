package org.siksnaghae.fgmate.api.product.repository;

import org.siksnaghae.fgmate.api.product.model.Product;
import org.siksnaghae.fgmate.api.product.model.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByRefrigeratorId(Long id);
    @Query("select new org.siksnaghae.fgmate.api.product.model.ProductDto(P) " +
            "from Product P where P.productId = :id")
    ProductDto findByProductIdDtl(Long id);
    Product findByProductId(Long id);
}
