package com.mabaya.online.sponsored.ads.exam.endpoint;

import com.mabaya.online.sponsored.ads.exam.AppProperties;
import com.mabaya.online.sponsored.ads.exam.entity.Campaign;
import com.mabaya.online.sponsored.ads.exam.entity.Product;
import com.mabaya.online.sponsored.ads.exam.manager.CampaignsManager;
import com.mabaya.online.sponsored.ads.exam.manager.ProductManager;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EndpointController {

    private final CampaignsManager campaignsManager;
    private final ProductManager productManager;

    private final AppProperties properties;

    @Autowired
    public EndpointController(CampaignsManager campaignsManager,ProductManager productManager,AppProperties properties)
    {
        this.campaignsManager = campaignsManager;
        this.productManager = productManager;
        this.properties = properties;
    }


    @RequestMapping(method = RequestMethod.POST,value = "/api/online-ads/category/add")
    public Campaign addCampaign(@RequestParam("name") @NonNull String name,
                                @ApiParam(value = "start-date",defaultValue = "15-10-2023",required = true) @RequestParam("start-date") @NonNull  String startDateString,
                                @RequestParam("bid") @NonNull  Double bid,
                                @RequestParam("ids") @NonNull  Long [] productIds) throws ParseException {
        List<Long>longs = Arrays.asList(productIds);
        List<Product>products = productManager.getProducts().stream()
                .filter(p -> longs.contains(p.getId()))
                .collect(Collectors.toList());
        Date startDate = new SimpleDateFormat("dd-MM-YYYY").parse(startDateString);
        Campaign c = new Campaign(name,startDate,bid,products);
        campaignsManager.addCampaign(c);
        return c;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/api/online-ads/ads/get")
    public Product getAd(@RequestParam("category") @NonNull String category)
    {
        List<Campaign>campaigns = campaignsManager.getActive();
        if (campaigns.isEmpty())
        {
            return null;
        }
        for (Campaign c:campaigns)
        {
            List<Product>products = c.getCategoryProducts(category);
            if (products != null)
            {
                return products.get(0);
            }
        }
        return campaigns.get(0).getDefaultProduct();
    }

    @Scheduled(cron = "@daily")
    public void testCampaigns()
    {
        for (Campaign c: campaignsManager.getCampaigns())
        {
            if (!campaignsManager.isActive(c,properties.getCampaignPeriod()))
            {
                c.setActive(false);
            }
        }
    }

}
