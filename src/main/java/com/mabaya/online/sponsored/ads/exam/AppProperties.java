package com.mabaya.online.sponsored.ads.exam;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppProperties {
    private Integer campaignPeriod;

    public void setCampaignPeriod(Integer campaignPeriod) {
        this.campaignPeriod = campaignPeriod;
    }

    public Integer getCampaignPeriod() {
        return campaignPeriod;
    }
}
