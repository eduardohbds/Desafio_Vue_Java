package com.hbds.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component // This tells Spring to create an instance of this class
public class DataLoader implements CommandLineRunner {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductRepository productRepository;

  @Override
  public void run(String... args) throws Exception {

    // Only load data if database is empty
    if (categoryRepository.count() == 0) {
      System.out.println("Loading sample data...");
      loadSampleData();
      System.out.println("Sample data loaded successfully!");
    } else {
      System.out.println("Data already exists, skipping sample data load.");
    }
  }

  private void loadSampleData() {
    // Create categories
    Category electronics = new Category("Electronics", "Electronic devices and gadgets");
    Category books = new Category("Books", "Physical and digital books");
    Category clothing = new Category("Clothing", "Apparel and fashion items");
    Category homeGarden = new Category("Home & Garden", "Home improvement and garden supplies");
    Category sports = new Category("Sports", "Sports equipment and accessories");

    // Save categories first (they need IDs for foreign keys)
    electronics = categoryRepository.save(electronics);
    books = categoryRepository.save(books);
    clothing = categoryRepository.save(clothing);
    homeGarden = categoryRepository.save(homeGarden);
    sports = categoryRepository.save(sports);

    // Create and save products
    productRepository.save(new Product("iPhone 15", new BigDecimal("999.99"), electronics));
    productRepository.save(new Product("MacBook Pro", new BigDecimal("1299.99"), electronics));
    productRepository.save(new Product("Java Programming Book", new BigDecimal("49.99"), books));
    productRepository.save(new Product("Spring Boot Guide", new BigDecimal("59.99"), books));
    productRepository.save(new Product("Summer T-Shirt", new BigDecimal("29.99"), clothing));
    productRepository.save(new Product("Jeans", new BigDecimal("79.99"), clothing));
    productRepository.save(new Product("Garden Hose", new BigDecimal("25.99"), homeGarden));
    productRepository.save(new Product("Lawn Mower", new BigDecimal("299.99"), homeGarden));
    productRepository.save(new Product("Basketball", new BigDecimal("24.99"), sports));
    productRepository.save(new Product("Running Shoes", new BigDecimal("89.99"), sports));
  }
}
