package com.example.turtlemart.repos;

import com.example.turtlemart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {

}
