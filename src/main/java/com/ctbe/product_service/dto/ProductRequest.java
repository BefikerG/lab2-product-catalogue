package com.ctbe.product_service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private double price;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockQty;

    @NotBlank(message = "Category is required")
    private String category;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String n) { this.name = n; }

    public double getPrice() { return price; }
    public void setPrice(double p) { this.price = p; }

    public int getStockQty() { return stockQty; }
    public void setStockQty(int q) { this.stockQty = q; }

    public String getCategory() { return category; }
    public void setCategory(String c) { this.category = c; }
}
