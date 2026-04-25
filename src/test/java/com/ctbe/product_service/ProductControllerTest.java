package com.ctbe.product_service;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.transaction.annotation.Transactional;

import com.ctbe.product_service.model.Product;
import com.ctbe.product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
  class ProductControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ProductRepository repo;
    @Autowired private ObjectMapper mapper;

    private Long savedId;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
        Product p = repo.save(new Product("Test Laptop", 999.99, 10, "Electronics"));
        savedId = p.getId();
    }

    @Test
    @DisplayName("1. GET /api/v1/products - Should return list")
    void shouldReturnAllProducts() throws Exception {
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Test Laptop")));
    }

    @Test
    @DisplayName("2. POST /api/v1/products - Should create product (201)")
    void shouldCreateProduct() throws Exception {
        String json = """
            {
                "name": "Mouse",
                "price": 25.0,
                "stockQty": 100,
                "category": "Peripherals"
            }
            """;

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.name", is("Mouse")));
    }

    @Test
    @DisplayName("3. POST /api/v1/products - Should fail validation (400)")
    void shouldFailWhenNameIsBlank() throws Exception {
        String invalidJson = """
            {
                "name": "",
                "price": 10.0,
                "stockQty": 5,
                "category": "Tech"
            }
            """;

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", is("Validation Error")))
                .andExpect(jsonPath("$.detail", containsString("Name is required")));
    }

    @Test
    @DisplayName("4. GET /api/v1/products/{id} - Should return 404 ProblemDetail")
    void shouldReturn404WhenNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/products/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title", is("Resource Not Found")))
                .andExpect(jsonPath("$.detail", is("Product 999 not found")));
    }
}