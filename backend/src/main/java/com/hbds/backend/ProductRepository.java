package com.hbds.backend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByCategoryId(Long categoryId);

  List<Product> findByProductNameContaining(String productName);

  @Query("Select p From Product p Join Fetch p.category")
  List<Product> findAllWithCategory();
}
