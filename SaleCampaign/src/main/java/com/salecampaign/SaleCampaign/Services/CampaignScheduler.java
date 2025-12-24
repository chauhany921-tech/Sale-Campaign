package com.salecampaign.SaleCampaign.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CampaignScheduler {

    @Autowired
    CampaignSchedulerService schedulerService;

    @Scheduled(cron = "2 * * * * *")
    public void startTodayCampaigns() {
        schedulerService.startTodayCampaign();
    }

    @Scheduled(cron = "10 * * * * *")
    public void endTodayCampaigns() {
        schedulerService.endCampaignsForToday();
    }
}

