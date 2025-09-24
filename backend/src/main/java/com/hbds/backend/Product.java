package com.hbds.backend;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "product_name", nullable = false)
  private String productName;
  @Column(name = "price", nullable = false)
  private BigDecimal price;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  private Category category;

  /**
   * @param id
   * @param productName
   * @param price
   * @param category
   */
  public Product(Long id, String productName, BigDecimal price, Category category) {
    this.id = id;
    this.productName = productName;
    this.price = price;
    this.category = category;
  }

  /**
   * @param productName
   * @param price
   * @param category
   */
  public Product(String productName, BigDecimal price, Category category) {
    this.productName = productName;
    this.price = price;
    this.category = category;
  }

  public Product() {
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @return the productName
   */
  public String getProductName() {
    return productName;
  }

  /**
   * @param productName the productName to set
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * @return the price
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * @param price the price to set
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * @return the category
   */
  public Category getCategory() {
    return category;
  }

  /**
   * @param category the category to set
   */
  public void setCategory(Category category) {
    this.category = category;
  }
}
