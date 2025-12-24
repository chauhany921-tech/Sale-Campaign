package com.salecampaign.SaleCampaign.DTO;

public class ProductDiscountDTO {

    private int productId;
    private int discount;

    public ProductDiscountDTO() {}
    public ProductDiscountDTO(int productId, int discount) {
        this.productId = productId;
        this.discount = discount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
