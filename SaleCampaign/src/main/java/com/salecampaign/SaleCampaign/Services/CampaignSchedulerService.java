package com.salecampaign.SaleCampaign.Services;

import com.salecampaign.SaleCampaign.Model.Campaign;
import com.salecampaign.SaleCampaign.Model.StatusEnum;
import com.salecampaign.SaleCampaign.Repository.CampaignRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CampaignSchedulerService {


    @Autowired
    CampaignRepo campaignRepo;

    @Autowired
    ProductService productService;


    public void startTodayCampaign() {
        LocalDate today = LocalDate.now();

        List<Campaign> campaigns = campaignRepo.findByStartDate(today);

        for (Campaign c : campaigns) {
            if (c.getStatus() == StatusEnum.UPCOMING ) {
                productService.applyDiscount(c);
                c.setStatus(StatusEnum.ACTIVE);
                campaignRepo.save(c);
                System.out.println("Campaign Activated...");
            }
        }

    }

    // Runs every day at 00:02 AM
    public void endCampaignsForToday() {
        LocalDate today = LocalDate.now();
        List<Campaign> campaigns = campaignRepo.findByEndDate(today);

        for (Campaign c : campaigns) {
            if (c.getStatus() == StatusEnum.ACTIVE ) {
                productService.revertDiscount(c);
                c.setStatus(StatusEnum.ENDED);
                campaignRepo.save(c);
                System.out.println("Campaign Ended...");
            }
        }
    }



}
