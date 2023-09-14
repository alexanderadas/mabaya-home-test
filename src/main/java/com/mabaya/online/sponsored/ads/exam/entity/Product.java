package com.mabaya.online.sponsored.ads.exam.entity;

import org.springframework.lang.NonNull;

import java.util.Objects;

public class Product
{
    private long id;
    private String title;
    private String category;
    private Double price;

    public Product() {
    }

    public Product(@NonNull Long id,@NonNull String category,@NonNull String title,@NonNull Double price)
    {
        this.id = id;
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
