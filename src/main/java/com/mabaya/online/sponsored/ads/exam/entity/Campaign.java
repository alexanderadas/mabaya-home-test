package com.mabaya.online.sponsored.ads.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import java.util.*;

@JsonIgnoreProperties("active")
public class Campaign {
    private String name;
    private Date startDate;
    private Double bid;

    private Product defaultProduct;


    private boolean active;

    private Map<String,List<Product>> categoryProducts;

    public Campaign(@NonNull String name,@NonNull Date startDate,@NonNull Double bid, @NonNull List<Product> products)
    {
        this.active = true;
        this.name = name;
        this.startDate = startDate;
        this.bid = bid;
        setCategoryProducts(products);
    }

    private void setCategoryProducts(List<Product>products)
    {
        if (products.isEmpty())
        {
            throw new IllegalArgumentException("No products to promote");
        }
        defaultProduct = products.get(0);
        categoryProducts = new HashMap<>();
        for (Product p:products)
        {
            String category = p.getCategory();
            categoryProducts.putIfAbsent(category,new ArrayList<>());
            categoryProducts.get(category).add(p);
        }
    }

    public boolean isActive() {
        return active;
    }

    public List<Product> getCategoryProducts(String category)
    {
        return categoryProducts.get(category);
    }

    public Product getDefaultProduct() {
        return defaultProduct;
    }

    public void setDefaultProduct(Product defaultProduct) {
        this.defaultProduct = defaultProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Map<String, List<Product>> getCategoryProducts() {
        return categoryProducts;
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Campaign campaign = (Campaign) object;
        return Objects.equals(name, campaign.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
