package com.astech.productsystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private double price;
    @NotNull
    private int quantity;
    @NotNull
    @NotEmpty
    private String imageUrl;
}
