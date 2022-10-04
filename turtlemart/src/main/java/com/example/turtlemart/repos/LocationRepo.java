package com.example.turtlemart.repos;

import com.example.turtlemart.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location,Integer> {
}
