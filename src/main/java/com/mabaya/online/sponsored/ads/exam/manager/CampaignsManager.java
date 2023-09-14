package com.mabaya.online.sponsored.ads.exam.manager;

import com.mabaya.online.sponsored.ads.exam.entity.Campaign;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignsManager
{
    private final List<Campaign> campaigns;

    public CampaignsManager()
    {
        campaigns = new ArrayList<>();
    }

    public void addCampaign(Campaign campaign)
    {
        if (campaigns.isEmpty())
        {
            campaigns.add(campaign);
        }
        boolean inserted = false;
        boolean sameName = false;
        for (int i = 0; i < campaigns.size(); i++)
        {
            Campaign current = campaigns.get(i);
            sameName = current.getName().equals(campaign.getName());
            if (!sameName && campaign.getBid() > current.getBid())
            {
                campaigns.add(i,campaign);
                inserted = true;
            }
        }
        if(!inserted && !sameName)
        {
            campaigns.add(campaign);//add to the end
        }
    }

    public List<Campaign> getActive()
    {
        return campaigns.stream().
                filter(Campaign::isActive)
                .collect(Collectors.toList());
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public boolean isActive(Campaign campaign, int period)
    {
        Calendar lastDayOfCampaign = Calendar.getInstance();
        lastDayOfCampaign.setTime(campaign.getStartDate());
        lastDayOfCampaign.add(Calendar.DATE,period);

        return System.currentTimeMillis() < lastDayOfCampaign.getTimeInMillis();
    }
}
