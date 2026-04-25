# 📦 Product Catalogue API — Lab 2

[![Java CI with Maven](https://github.com/BefikerG/lab2-product-catalogue/actions/workflows/maven.yml/badge.svg)](https://github.com/BefikerG/lab2-product-catalogue/actions/workflows/maven.yml)
![Java Version](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)

A professional, production-ready RESTful API built with Spring Boot 3 for managing a product inventory. This project demonstrates the implementation of the **DTO Pattern**, **Global Exception Handling**, and **Automated Integration Testing**.

---

## 🚀 Key Features

* **Full CRUD Lifecycle**: Create, Read, Update, and Delete products with ease.
* **Data Validation**: Strict input rules using Jakarta Validation (e.g., non-blank names, positive prices).
* **Standardized Errors**: Implements **RFC 9457 (Problem Details)** for consistent API error responses.
* **Auto-Generated Documentation**: Fully interactive API playground via **Swagger UI / OpenAPI 3**.
* **CI/CD Pipeline**: Automated testing on every push via GitHub Actions.

---

## 🛠️ Tech Stack

* **Backend**: Java 17, Spring Boot 3
* **Database**: H2 (In-Memory)
* **Documentation**: SpringDoc OpenAPI
* **Testing**: JUnit 5, MockMvc, AssertJ
* **Build Tool**: Maven

---

## 📖 API Documentation

Once the application is running, you can explore and test the endpoints visually:

🔗 **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Sample Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/v1/products` | Retrieve all products |
| `POST` | `/api/v1/products` | Add a new product (validates DTO) |
| `GET` | `/api/v1/products/{id}` | Get specific product details |
| `PUT` | `/api/v1/products/{id}` | Update existing product |
| `DELETE` | `/api/v1/products/{id}` | Remove product from inventory |

---

## 🧪 Running the Tests

To verify the logic and business rules:
```bash
mvn clean test
