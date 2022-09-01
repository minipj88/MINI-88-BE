package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class FinancialProductSearchControllerTest {
    @Autowired
    private MockMvc mockMvc; // mockMvc 생성

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testController() throws Exception{

        mockMvc.perform(get("/api/searchLoan")
                .param("financialCompanyName","부산은행")
                .param("productName", "ONE신용대출"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
