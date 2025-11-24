package com.UH.OtherLevel.domain.model;


public class Venue {
    private Long id;
    private String name;
    private String address;
    private Integer capacity;

    public Venue() {
    }

    public Venue(Long id, String name, Integer capacity, String address) {
        if (capacity < 10){
            throw new IllegalArgumentException("Capacity must be at least 10");
        }

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
        if (capacity < 10){
            throw new IllegalArgumentException("Capacity must be at least 10");
        }
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
