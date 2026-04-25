package com.ctbe.product_service.service;

import com.ctbe.product_service.dto.ProductRequest;
import com.ctbe.product_service.dto.ProductResponse;
import com.ctbe.product_service.exception.ResourceNotFoundException;
import com.ctbe.product_service.model.Product;
import com.ctbe.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public ProductResponse create(ProductRequest req) {
        Product product = toEntity(req);
        return toResponse(repo.save(product));
    }

    // READ ALL
    public List<ProductResponse> findAll() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    // READ ONE
    public ProductResponse findById(Long id) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return toResponse(product);
    }

    // UPDATE
    public ProductResponse update(Long id, ProductRequest req) {
        Product existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        
        existing.setName(req.getName());
        existing.setPrice(req.getPrice());
        existing.setStockQty(req.getStockQty());
        existing.setCategory(req.getCategory());
        
        return toResponse(repo.save(existing));
    }

    // DELETE
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repo.deleteById(id);
    }

    
    private ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(), 
                p.getName(), 
                p.getPrice(), 
                p.getStockQty(), 
                p.getCategory()
        );
    }

    
    private Product toEntity(ProductRequest req) {
        return new Product(
                req.getName(), 
                req.getPrice(), 
                req.getStockQty(), 
                req.getCategory()
        );
    }
}
