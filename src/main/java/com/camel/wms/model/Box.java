package com.camel.wms.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "Boxes")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer fullness;
    private Integer capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public Box() {
    }

    public Box(Integer fullness, Integer capacity) {
        this.fullness = fullness;
        this.capacity = capacity;
    }

    public Box(Integer fullness, Integer capacity, Position position, Product product) {
        this.fullness = fullness;
        this.capacity = capacity;
        this.position = position;
        this.product = product;
    }

    public Box(Integer fullness, Integer capacity, Position position) {
        this.fullness = fullness;
        this.capacity = capacity;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFullness() {
        return fullness;
    }

    public void setFullness(Integer fullness) {
        this.fullness = fullness;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
