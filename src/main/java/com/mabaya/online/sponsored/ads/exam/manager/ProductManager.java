package com.mabaya.online.sponsored.ads.exam.manager;

import com.mabaya.online.sponsored.ads.exam.entity.Product;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManager {
    private final List<Product> products;

    public ProductManager()
    {
        products = new ArrayList<>();
    }

    public boolean add(@NonNull Product product)
    {
        boolean notExist = !products.contains(product);
        if (notExist)
        {
            products.add(product);
        }
        return notExist;
    }

    public List<Product> getProducts() {
        return products;
    }
}
