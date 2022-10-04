package com.example.turtlemart.models;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "BALANCE")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int inventorySlot;

    private int location;

    private int product;


    private double balance;
}
