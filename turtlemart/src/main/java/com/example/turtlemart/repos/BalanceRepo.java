package com.example.turtlemart.repos;

import com.example.turtlemart.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface BalanceRepo extends JpaRepository<Balance, Integer> {
}
