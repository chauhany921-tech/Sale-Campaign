package com.salecampaign.SaleCampaign.Services;

import com.salecampaign.SaleCampaign.DTO.CreateCampaignDTO;
import com.salecampaign.SaleCampaign.DTO.ProductDiscountDTO;
import com.salecampaign.SaleCampaign.DTO.ResponseDTO;
import com.salecampaign.SaleCampaign.Exceptions.NotFoundException;
import com.salecampaign.SaleCampaign.Model.Campaign;
import com.salecampaign.SaleCampaign.Model.Product;
import com.salecampaign.SaleCampaign.Model.ProductDiscount;
import com.salecampaign.SaleCampaign.Model.StatusEnum;
import com.salecampaign.SaleCampaign.Repository.CampaignRepo;
import com.salecampaign.SaleCampaign.Repository.ProductDiscountRepo;
import com.salecampaign.SaleCampaign.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class CampaignService {

    @Autowired
    CampaignRepo campaignRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductDiscountRepo campaignDiscountRepo;

    @Autowired
    ProductService productService;

    public ResponseDTO createCampaign(CreateCampaignDTO dto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();

        LocalDate startDate;
        LocalDate endDate;

        //  Date parsing (throw exception)
        try {
            startDate = LocalDate.parse(dto.getStartDate(), formatter);
            endDate = LocalDate.parse(dto.getEndDate(), formatter);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Date must be in dd/MM/yyyy format");
        }

        // Date validation
        if (!startDate.isAfter(today)) {
            throw new IllegalArgumentException("Start date must be after today");
        }

        if (!endDate.isAfter(today)) {
            throw new IllegalArgumentException("End date must be after today");
        }

        // Create campaign
        Campaign campaign = new Campaign();
        campaign.setTitle(dto.getTitle());
        campaign.setStartDate(startDate);
        campaign.setEndDate(endDate);

        // Past campaign (only save history)
        if (endDate.isBefore(today)) {
            campaign.setStatus(StatusEnum.ENDED);
            campaignRepo.save(campaign);

            for (ProductDiscountDTO discountDTO : dto.getCampaignDiscount()) {
                Product product = productRepo.findById(discountDTO.getProductId())
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Product not found: " + discountDTO.getProductId()));

                int newPrice =
                        (int) (product.getPrice() * discountDTO.getDiscount() / 100);

                productService.saveProductHistory(
                        product,
                        campaign.getStartDate().toString(),
                        newPrice
                );
            }

            return ResponseDTO.success(
                    "Past campaign saved. Discounts not applied.",
                    campaign
            );
        }


        campaign.setStatus(StatusEnum.UPCOMING);
        campaignRepo.save(campaign);

        for (ProductDiscountDTO discountDTO : dto.getCampaignDiscount()) {

            Product product = productRepo.findById(discountDTO.getProductId())
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Product not found: " + discountDTO.getProductId()));

            ProductDiscount discount = new ProductDiscount();
            discount.setCampaign(campaign);
            discount.setProduct(product);
            discount.setDiscount(discountDTO.getDiscount());

            campaignDiscountRepo.save(discount);
        }

        return ResponseDTO.success("Campaign created successfully", campaign);
    }

    public Campaign getCampaignById(int cid) {
        if (cid < 0 ) {
            throw new IllegalArgumentException("Invalid Campaign Id");
        }
        return campaignRepo.findById(cid)
                .orElseThrow(() ->
                        new NotFoundException("Campaign not found with id: " + cid));
    }
}
