package com.hbds.backend;

import java.math.BigDecimal;

public class ProductDTO {
  private String name;
  private BigDecimal price;
  private Long categoryId;

  ProductDTO(String name,
      BigDecimal price,
      Long categoryId) {
    this.name = name;
    this.price = price;
    this.categoryId = categoryId;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
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
   * @return the categoryId
   */
  public Long getCategoryId() {
    return categoryId;
  }
}
