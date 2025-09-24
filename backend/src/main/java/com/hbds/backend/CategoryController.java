package com.hbds.backend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

  @Autowired // O jeito do Spring injetar o repositorio na classe
  public CategoryRepository categoryRepository;

  @GetMapping // Isso avisa para o spring para usar esse metodo é um Get
  public ResponseEntity<List<Category>> getAllCategories() { // ResponseEntity serve como um response normal
    List<Category> categories = categoryRepository.findAll();
    return ResponseEntity.ok(categories);
  }

  @PostMapping
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category savedCategory = categoryRepository.save(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
    return categoryRepository.findById(id)
        .map(existingCategory -> {
          existingCategory.setName(category.getName());
          existingCategory.setDescription(category.getDescription());
          return ResponseEntity.ok(categoryRepository.save(existingCategory));
        })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    if (categoryRepository.existsById(id)) {
      categoryRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
