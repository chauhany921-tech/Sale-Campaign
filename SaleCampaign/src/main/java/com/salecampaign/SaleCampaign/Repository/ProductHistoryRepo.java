package com.salecampaign.SaleCampaign.Repository;

import com.salecampaign.SaleCampaign.Model.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistoryRepo extends JpaRepository<ProductHistory, Integer> {
}
