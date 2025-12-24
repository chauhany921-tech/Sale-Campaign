package com.salecampaign.SaleCampaign.Repository;

import com.salecampaign.SaleCampaign.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CampaignRepo extends JpaRepository<Campaign, Integer> {
    List<Campaign> findByStartDate(LocalDate date);
    List<Campaign> findByEndDate(LocalDate date);

}
