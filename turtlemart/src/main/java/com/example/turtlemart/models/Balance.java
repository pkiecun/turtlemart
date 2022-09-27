package com.example.turtlemart.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Balance {

    private int product_Id;

    private int location_Id;

    private double balance;
}
