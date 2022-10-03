package com.example.turtlemart.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostalCode {
    private String adminCode2;
    private String adminCode1;
    private String adminName2;
    private float lng;
    private String distance;
    private String countryCode;
    private String postalCode;
    private String adminName1;
    private String placeName;
    private float lat;


}
