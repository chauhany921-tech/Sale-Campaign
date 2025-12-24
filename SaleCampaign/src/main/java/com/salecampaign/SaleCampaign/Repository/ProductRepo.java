package com.salecampaign.SaleCampaign.Repository;

import com.salecampaign.SaleCampaign.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
