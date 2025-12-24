package com.salecampaign.SaleCampaign.DTO;

import java.util.List;

public class CreateCampaignDTO {
        private String title;
        private String startDate; // Can be LocalDate if you use @JsonFormat
        private String endDate;
        private List<ProductDiscountDTO> campaignDiscount;

    public CreateCampaignDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<ProductDiscountDTO> getCampaignDiscount() {
        return campaignDiscount;
    }

    public void setCampaignDiscount(List<ProductDiscountDTO> campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }
}
