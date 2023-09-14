package com.mabaya.online.sponsored.ads.exam;

import com.mabaya.online.sponsored.ads.exam.entity.Product;
import com.mabaya.online.sponsored.ads.exam.manager.ProductManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class DataGenerator {

    private final ProductManager productManager;

    @Autowired
    public DataGenerator(ProductManager productManager)
    {
        this.productManager = productManager;
    }

    @PostConstruct
    public void generate()
    {
        long [] ids = LongStream.range(0,10).toArray();
        String [] categories = {"Fruits","Hardware", "Media", "Storage"};
        String [] titles = {"Orange","Apple", "CPU","Video Card", "Wire", "video", "Box","Bag","Closet","Locker"};
        double [] prices = new double[10];
        Random random = new Random();
        for (int i = 0; i < prices.length; i++)
        {
            prices[i] = random.nextDouble() * 25;
        }

        productManager.add(new Product(ids[0],categories[0],titles[0],prices[0]));
        productManager.add(new Product(ids[1],categories[0],titles[1],prices[1]));
        productManager.add(new Product(ids[2],categories[1],titles[2],prices[2]));
        productManager.add(new Product(ids[3],categories[1],titles[3],prices[3]));
        productManager.add(new Product(ids[4],categories[1],titles[4],prices[4]));
        productManager.add(new Product(ids[5],categories[2],titles[5],prices[5]));
        productManager.add(new Product(ids[6],categories[3],titles[6],prices[6]));
        productManager.add(new Product(ids[7],categories[3],titles[7],prices[7]));
        productManager.add(new Product(ids[8],categories[3],titles[8],prices[8]));
        productManager.add(new Product(ids[9],categories[3],titles[9],prices[9]));
    }
}
