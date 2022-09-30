package com.example.turtlemart.services;

import com.example.turtlemart.models.*;
import com.example.turtlemart.repos.BalanceRepo;
import com.example.turtlemart.repos.LocationRepo;
import com.example.turtlemart.repos.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    private final ProductRepo pr;

    private final BalanceRepo br;

    private final LocationRepo lr;

    public SearchService(ProductRepo pr,BalanceRepo br,LocationRepo lr){
        this.pr = pr;
        this.br = br;
        this.lr = lr;
    }

    public List<Result> search(String query){
        List<Product> items = pr.findAll();
        List<Product> identifiers = new ArrayList<>();


        for(Product item: items){
            if(item.getProduct().equalsIgnoreCase(query) || item.getDepartment().equalsIgnoreCase(query)){
                identifiers.add(item);
            }
        }

        if(identifiers.isEmpty()){
            List<Result> emptyResult = new ArrayList<>();
            emptyResult.add(new Result());
            return emptyResult;
        }
        List<Balance> stocks = br.findAll();
        List<Balance> inStock = new ArrayList<>();
        for(Balance stock : stocks){
            for(Product item: identifiers){
                if(item.getItem() == stock.getProduct()){
                    inStock.add(stock);
                }
            }
        }
        List<Location> places = lr.findAll();
        List<Result> results = new ArrayList<>();
        //List<Location> locations = new ArrayList<>();
        for(Location place: places){
            List<Inventory> locations = new ArrayList<>();
                for(Balance balance: inStock) {
                   if(balance.getLocation() == place.getId()){
                       for(Product product: identifiers){
                           if(balance.getProduct() == product.getItem()){
                               locations.add(new Inventory(product.getProduct(), balance.getBalance()));
                           }
                       }

                   }
                }
                if(!locations.isEmpty()){
                    Result result = new Result(place.getCity(), place.getZipcode(), locations);

                    results.add(result);

                }
            }
        //List<Location> available = new ArrayList<>();
        return results;
    }

}
