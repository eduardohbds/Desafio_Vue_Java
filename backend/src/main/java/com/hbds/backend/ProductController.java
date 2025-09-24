package com.hbds.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productRepository.findAllWithCategory();
    return ResponseEntity.ok(products);
  }

  @PostMapping
  public ResponseEntity<Product> createProducts(@RequestBody ProductDTO productDTO) {
    return categoryRepository.findById(productDTO.getCategoryId())
        .map(category -> {
          Product product = new Product();
          product.setProductName(productDTO.getName());
          product.setPrice(productDTO.getPrice());
          product.setCategory(category);

          Product savedProduct = productRepository.save(product);
          return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        })
        .orElse(ResponseEntity.badRequest().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
    Product existingProduct = productRepository.findById(id).orElse(null);
    if (existingProduct == null) {
      return ResponseEntity.notFound().build();
    }

    Category category = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
    if (category == null) {
      return ResponseEntity.badRequest().build();
    }

    existingProduct.setProductName(productDTO.getName());
    existingProduct.setPrice(productDTO.getPrice());
    existingProduct.setCategory(category);

    return ResponseEntity.ok(productRepository.save(existingProduct));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
