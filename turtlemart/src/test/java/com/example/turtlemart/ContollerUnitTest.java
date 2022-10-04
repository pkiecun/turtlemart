package com.example.turtlemart;

import com.example.turtlemart.controllers.SearchController;
import com.example.turtlemart.models.Balance;
import com.example.turtlemart.models.Location;
import com.example.turtlemart.models.Product;
import com.example.turtlemart.models.Result;
import com.example.turtlemart.repos.BalanceRepo;
import com.example.turtlemart.repos.LocationRepo;
import com.example.turtlemart.repos.ProductRepo;
import com.example.turtlemart.services.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TurtlemartApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
//@WebFluxTest(controllers = SearchController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})
//@Import(SearchService.class)
public class ContollerUnitTest {
    @Autowired
    private ProductRepo pr;
    @Autowired
    private LocationRepo lr;
    @Autowired
    private BalanceRepo br;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper om = new ObjectMapper();

//    @Autowired
//    private WebTestClient mockMvc;

    @Test
    public void zipCodeTest() throws Exception{
        LinkedHashMap<String,String> theD = new LinkedHashMap<>();
        theD.put("body","apple");
        theD.put("zipCode", "10001");
        theD.put("radius","10");
//        mockMvc.get().uri("http://localhost:3000/search").header(HttpHeaders.ACCEPT, "application/json").
//                .exchange().expectStatus().isOk();
        mockMvc.perform(post("/search").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(theD)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].zip_Code").value("10001"))
                .andExpect(jsonPath("$.[0].location_Name").value("New York City"))
                .andExpect(jsonPath("$.[0].inventory.[0].name").value("apple"))
                .andExpect(jsonPath("$.[0].inventory.[0].value").value("30.0"));
//        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(loginRequest))
//                ).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.email").value("testuser@gmail.com"))
//                .andExpect(jsonPath("$.password").value("")).andExpect(jsonPath("$.firstName").value("a"))
//                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.admin").value(false));
    }
}
