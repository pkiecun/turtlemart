package com.example.turtlemart.serviceTest;


import com.example.turtlemart.models.Balance;
import com.example.turtlemart.models.Location;
import com.example.turtlemart.models.Product;
import com.example.turtlemart.models.Result;
import com.example.turtlemart.repos.BalanceRepo;
import com.example.turtlemart.repos.LocationRepo;
import com.example.turtlemart.repos.ProductRepo;
import com.example.turtlemart.services.SearchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchServiceTest {


    @Mock
    private ProductRepo pr;
    @Mock
    private LocationRepo lr;
    @Mock
    private BalanceRepo br;





    @Test
    public void searchTest(){
        SearchService ssv = new SearchService(pr, br, lr);
        Product mockProduct = new Product(1,"apple",220011, "fruit");
        Location mockLocation = new Location(1,"Kansas", 99999);
        Balance mockBalance = new Balance(1,1,1,10);
        List<Product> items = new ArrayList<>();
        List<Location> places = new ArrayList<>();
        List<Balance> balances = new ArrayList<>();
        items.add(mockProduct);
        places.add(mockLocation);
        balances.add(mockBalance);
        when((pr).findAll()).thenReturn(items);
        when((lr).findAll()).thenReturn(places);
        when((br).findAll()).thenReturn(balances);

        List<Result> results = ssv.search("apple");

        assertEquals(99999, results.get(0).getZip_Code(), "right zip code");
        assertEquals("Kansas", results.get(0).getLocation_Name(), "right location name");
        assertEquals(10, results.get(0).getInventory().get(0).getValue(), "right balance");
    }

}
