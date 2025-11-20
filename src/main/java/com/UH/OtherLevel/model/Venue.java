package com.UH.OtherLevel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Venue {
    private Long id;
    private String name;
    private String address;
    private Integer capacity;

    public Venue() {
    }

    public Venue(Long id, String name, Integer capacity, String address) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
