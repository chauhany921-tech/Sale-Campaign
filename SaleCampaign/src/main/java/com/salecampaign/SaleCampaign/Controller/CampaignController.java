package com.salecampaign.SaleCampaign.Controller;

import com.salecampaign.SaleCampaign.Services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @GetMapping("/{cid}")
    public ResponseEntity<?> getById(@PathVariable int cid) {
        return ResponseEntity.ok(campaignService.getCampaignById(cid));
    }


}
