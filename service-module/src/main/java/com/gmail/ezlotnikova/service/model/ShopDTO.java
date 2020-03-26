package com.gmail.ezlotnikova.service.model;

import javax.validation.constraints.Size;

public class ShopDTO {

    private Long id;
    @Size(min = 5, max = 40, message = "Must be between 5 and 40 characters long")
    private String name;
    @Size(min = 5, max = 100, message = "Must be between 5 and 100 characters long")
    private String location;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}