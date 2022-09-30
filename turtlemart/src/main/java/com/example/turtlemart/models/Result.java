package com.example.turtlemart.models;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Result {
    private String location_Name;
    private int zip_Code;
    private List<Inventory> inventory;
}
