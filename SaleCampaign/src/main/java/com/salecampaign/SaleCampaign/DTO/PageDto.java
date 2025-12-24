package com.salecampaign.SaleCampaign.DTO;

import com.salecampaign.SaleCampaign.Model.Product;

import java.util.List;

public class PageDto {

    private int pageno;
    private int totalPage;
    private int pageSize;
    private List<Product> productDtoList;

    public PageDto() {
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Product> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<Product> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
