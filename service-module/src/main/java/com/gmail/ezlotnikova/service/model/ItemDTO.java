package com.gmail.ezlotnikova.service.model;

import java.math.BigDecimal;
import javax.validation.constraints.Size;

public class ItemDTO {

    private Long id;
    @Size(min = 5, max = 40, message = "Must be between 5 and 40 characters long")
    private String name;
    @Size(min = 5, max = 100, message = "Must be between 5 and 100 characters long")
    private String description;
    private BigDecimal price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}