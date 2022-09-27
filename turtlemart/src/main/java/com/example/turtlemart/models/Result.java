package com.example.turtlemart.models;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Result {
    private String location_Name;
    private int zip_Code;
    private Map<String, Double> inventory;
}
