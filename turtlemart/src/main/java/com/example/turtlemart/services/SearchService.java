package com.example.turtlemart.services;

import com.example.turtlemart.models.Balance;
import com.example.turtlemart.models.Product;
import com.example.turtlemart.models.Result;
import com.example.turtlemart.repos.BalanceRepo;
import com.example.turtlemart.repos.LocationRepo;
import com.example.turtlemart.repos.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

//    public List<Result> search(String query){
//        List<Product> items = pr.findAll();
//        List<Integer> identifiers = new ArrayList<>();
//        for(Product item: items){
//            if(item.getProduct().equalsIgnoreCase(query) || item.getDepartment().equalsIgnoreCase(query)){
//                identifiers.add(item.getItem());
//            }
//        }
//        List<Balance> stocks = br.findAll();
//        for(Balance stock : stocks){
//            for(Integer item: identifiers){
//            if(){
//
//            }
//            }
//        }
//        return null;
//    }

}
