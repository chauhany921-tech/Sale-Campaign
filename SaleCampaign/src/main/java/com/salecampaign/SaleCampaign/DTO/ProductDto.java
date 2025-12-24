package com.salecampaign.SaleCampaign.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;


public class ProductDto {


    @NotBlank(message = "Product name cannot be empty")
    @Size(min = 3, max = 50, message = "Product name must be 3-50 characters")
    private String name;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1")
    private int price;

    @NotNull(message = "Current price is required")
    @Min(value = 0, message = "Current price cannot be negative")
    private int currentPrice;

    @Min(value = 0, message = "Discount cannot be negative")
    private int discount;

    @NotNull(message = "Inventory count is required")
    @Min(value = 0, message = "Inventory count cannot be negative")
    private int inventoryCount;

    public ProductDto() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}
