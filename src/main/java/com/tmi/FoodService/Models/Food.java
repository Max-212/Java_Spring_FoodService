package com.tmi.FoodService.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String image;

    private String title;

    private Double price;

    private Double weight;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_foods",
            joinColumns = {@JoinColumn(name = "food_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    private List<Order> orders;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
