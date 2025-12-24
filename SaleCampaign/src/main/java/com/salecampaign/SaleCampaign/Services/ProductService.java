package com.salecampaign.SaleCampaign.Services;


import com.salecampaign.SaleCampaign.DTO.PageDto;
import com.salecampaign.SaleCampaign.DTO.ProductDto;
import com.salecampaign.SaleCampaign.DTO.ResponseDTO;
import com.salecampaign.SaleCampaign.Exceptions.NotFoundException;
import com.salecampaign.SaleCampaign.Model.Campaign;
import com.salecampaign.SaleCampaign.Model.Product;
import com.salecampaign.SaleCampaign.Model.ProductDiscount;
import com.salecampaign.SaleCampaign.Model.ProductHistory;
import com.salecampaign.SaleCampaign.Repository.ProductDiscountRepo;
import com.salecampaign.SaleCampaign.Repository.ProductHistoryRepo;
import com.salecampaign.SaleCampaign.Repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductDiscountRepo discountRepo;

    @Autowired
    ProductHistoryRepo historyRepo;

    public ResponseDTO saveProducts(List<ProductDto> productList) {

        List<Product> products = new ArrayList<>();

        for (ProductDto dto : productList) {
            Product p = new Product();
            p.setName(dto.getName());
            p.setPrice(dto.getPrice());
            p.setCurrentPrice(dto.getCurrentPrice());
            p.setDiscount(dto.getDiscount());
            p.setInventoryCount(dto.getInventoryCount());
            products.add(p);
        }

        productRepo.saveAll(products);
        saveProductHistory(products);

        return ResponseDTO.success("Products added successfully", productList);
    }

    public ResponseDTO getProducts(Integer pageNum, Integer pageSize) {

        if (pageNum < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid pagination values");
        }

        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Product> page = productRepo.findAll(pageable);

        if (pageNum >= page.getTotalPages()) {
            throw new IllegalArgumentException("Page number exceeds total pages");
        }

        PageDto dto = new PageDto();
        dto.setPageno(pageNum);
        dto.setPageSize(page.getSize());
        dto.setTotalPage(page.getTotalPages());
        dto.setProductDtoList(page.getContent());

        return ResponseDTO.success("Products fetched successfully", dto);
    }

    public Product getById(int pid) {
        if (pid < 0 ) {
            throw new IllegalArgumentException("Invalid Product Id");
        }
        return productRepo.findById(pid)
                .orElseThrow(() ->
                        new NotFoundException("Product not found with id: " + pid));
    }
@Transactional
    public void applyDiscount(Campaign c) {
        List<ProductDiscount> list = discountRepo.findByCampaign(c);

        for (ProductDiscount pd : list) {
            Product p = pd.getProduct();
            int newPrice = (int) (p.getPrice() - (p.getPrice() * pd.getDiscount() / 100));

            p.setCurrentPrice(newPrice);
            p.setDiscount(p.getDiscount() + pd.getDiscount());
            productRepo.save(p);

            saveProductHistory(p, c.getStartDate().toString(), newPrice);
        }
    }

    public void revertDiscount(Campaign c) {
        List<ProductDiscount> list = discountRepo.findByCampaign(c);

        for (ProductDiscount pd : list) {
            Product p = pd.getProduct();
            int originalPrice = (int) ((p.getCurrentPrice() * 100) / (100 - pd.getDiscount()));

            p.setCurrentPrice(originalPrice);
            p.setDiscount(p.getDiscount() - pd.getDiscount());
            productRepo.save(p);

            saveProductHistory(p, c.getEndDate().toString(), originalPrice);
        }
    }

    public void saveProductHistory(Product p, String date, int price) {
        ProductHistory h = new ProductHistory();
        h.setProduct(p);
        h.setPrice(price);
        h.setDate(date);
        historyRepo.save(h);
    }

    private void saveProductHistory(List<Product> products) {
        for (Product p : products) {
            saveProductHistory(p, LocalDate.now().toString(), p.getPrice());
        }
    }

}
