package com.tmi.FoodService.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<Food> foods;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
