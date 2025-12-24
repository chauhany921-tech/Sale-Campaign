package com.salecampaign.SaleCampaign.Repository;

import com.salecampaign.SaleCampaign.Model.Campaign;
import com.salecampaign.SaleCampaign.Model.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDiscountRepo extends JpaRepository<ProductDiscount, Integer> {
    List<ProductDiscount> findByCampaign(Campaign c);
}
