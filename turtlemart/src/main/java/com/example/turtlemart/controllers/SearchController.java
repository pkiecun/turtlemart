package com.example.turtlemart.controllers;

import com.example.turtlemart.models.PostCodeArray;
import com.example.turtlemart.models.PostalCode;
import com.example.turtlemart.models.Result;
import com.example.turtlemart.repos.BalanceRepo;
import com.example.turtlemart.repos.LocationRepo;
import com.example.turtlemart.services.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SearchController {
    @Autowired
    private SearchService ssv;

    private ObjectMapper om;

    @PostMapping("/search")
    public ResponseEntity<List<Result>> searchByString(@RequestBody LinkedHashMap<String, String> search) throws IOException {

        String actualSearch = search.get("body");
        if(search.get("zipCode") != null) {
            int zipCode = Integer.parseInt(search.get("zipCode"));
            int radius = Integer.parseInt(search.get("radius"));

            URL url = new URL("http://api.geonames.org/findNearbyPostalCodesJSON?postalcode=" + zipCode + "&country=US&radius="+ radius + "&username=kikaniparth");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int status = con.getResponseCode();
            Reader streamReader = null;

            if (status > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
                System.out.println(streamReader);

            } else {
                streamReader = new InputStreamReader(con.getInputStream());
                //System.out.println(streamReader.read(CharBuffer.wrap("postalCode")));
                //LinkedHashMap<String, String[]> pc = om.readValue(con.getInputStream(), LinkedHashMap.class);

                //System.out.println(pc);
            }
            BufferedReader in = new BufferedReader(

                    new InputStreamReader(con.getInputStream()));



            String inputLine;
            //PostCodeArray pc = new PostCodeArray(con.getInputStream());
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {

                //PostalCode ps = new PostalCode(om.readValue());
                //PostCodeArray pc = om.readValue(in.readLine(), PostCodeArray.class);
                //System.out.println(pc);
                content.append(inputLine);
            }
            in.close();
            System.out.println(content);
            con.disconnect();
        }
        //search.replace("", "");

        System.out.println(actualSearch);
        //String newSearch = search.substring(0,5);

        //System.out.println(ssv.search(newSearch).toString());
        return ResponseEntity.ok(ssv.search(actualSearch));
    }




}
