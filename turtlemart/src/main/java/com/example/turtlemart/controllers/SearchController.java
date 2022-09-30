package com.example.turtlemart.controllers;

import com.example.turtlemart.models.Result;
import com.example.turtlemart.repos.BalanceRepo;
import com.example.turtlemart.repos.LocationRepo;
import com.example.turtlemart.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SearchController {
    @Autowired
    private SearchService ssv;

    @PostMapping("/search")
    public ResponseEntity<List<Result>> searchByString(@RequestBody LinkedHashMap<String, String> search) {

        String actualSearch = search.get("body");
        //search.replace("", "");

        System.out.println(actualSearch);
        //String newSearch = search.substring(0,5);

        //System.out.println(ssv.search(newSearch).toString());
        return ResponseEntity.ok(ssv.search(actualSearch));
    }




}
