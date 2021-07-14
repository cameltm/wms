package com.camel.wms.model;

import javax.persistence.*;

@Entity
@Table(name = "Positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private Integer fullness;
    private Integer capacity;

    public Position() {
    }

    public Position(Integer fullness, Integer capacity) {
        this.fullness = fullness;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}