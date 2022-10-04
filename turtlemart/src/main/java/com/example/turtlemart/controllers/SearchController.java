package com.example.turtlemart.controllers;

import com.example.turtlemart.models.Balance;
import com.example.turtlemart.models.Location;
import com.example.turtlemart.models.Result;
import com.example.turtlemart.repos.LocationRepo;
import com.example.turtlemart.services.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            String zipCode = search.get("zipCode");
            String radius = search.get("radius");

            URL url = new URL("http://api.geonames.org/findNearbyPostalCodesJSON?postalcode=" + zipCode + "&country=US&maxRows=500&radius="+ radius + "&username=kikaniparth");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int status = con.getResponseCode();
            Reader streamReader = null;
            List<String> allMatches = new ArrayList<>();

            if (status > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
                System.out.println(streamReader);

            } else {
                streamReader = new InputStreamReader(con.getInputStream());

            }
            BufferedReader in = new BufferedReader(

                    new InputStreamReader(con.getInputStream()));



            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);

                Matcher m = Pattern.compile("([\"'])\\d{5}([\"'])")
                        .matcher(inputLine);
                while (m.find()) {
                    allMatches.add(m.group().replaceAll("([\"'])", ""));
                }
                System.out.println(allMatches.toString());

            }
            in.close();
            System.out.println(content);
            con.disconnect();
            List<Result> unfilteredMatches = ssv.search(actualSearch);
            List<Result> finalMatches = new ArrayList<>();
            for (Result filtered : unfilteredMatches) {
                for(String match : allMatches){
                    if(filtered.getZip_Code() == Integer.parseInt(match)){
                        finalMatches.add(filtered);
                    }
                }
            }
            return ResponseEntity.ok(finalMatches);

        }
        System.out.println(actualSearch);

        return ResponseEntity.ok(ssv.search(actualSearch));
    }




}
