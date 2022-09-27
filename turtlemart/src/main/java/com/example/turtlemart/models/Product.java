package com.example.turtlemart.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private int product_Id;

    private String product_name;

    private int department_Id;

}
