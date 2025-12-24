package com.salecampaign.SaleCampaign.Controller;

import com.salecampaign.SaleCampaign.DTO.CreateCampaignDTO;
import com.salecampaign.SaleCampaign.DTO.ProductDto;
import com.salecampaign.SaleCampaign.Services.CampaignService;
import com.salecampaign.SaleCampaign.Services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CampaignService campaignService;
    @PostMapping("/save-products")
    public ResponseEntity<?> saveProducts(
            @RequestBody @Valid List<@Valid ProductDto> products) {
        return ResponseEntity.ok(productService.saveProducts(products));
    }

    @GetMapping("/{pid}")
    public ResponseEntity<?> getById(@PathVariable int pid) {
        return ResponseEntity.ok(productService.getById(pid));
    }



    @GetMapping("/products")
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(productService.getProducts(pageNum, pageSize));
    }

    @PostMapping("/discount")
    public ResponseEntity<?> createCampaign(@RequestBody CreateCampaignDTO dto) {
        return ResponseEntity.ok(campaignService.createCampaign(dto));
    }


}
