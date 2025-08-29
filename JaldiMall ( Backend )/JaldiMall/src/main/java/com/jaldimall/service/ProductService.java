package com.jaldimall.service;

import com.jaldimall.exception.ProductException;
import com.jaldimall.model.Product;
import com.jaldimall.model.Seller;
import com.jaldimall.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product createProduct(CreateProductRequest req, Seller seller);
    void deleteProduct(Long prodId) throws ProductException;
    Product updateProduct(Long prodId, Product product) throws ProductException;
    Product findProductById(Long prodId) throws ProductException;
    List<Product> searchProducts(String query);
    public Page<Product> getAllProducts(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber

    );
    List<Product> getProductBySellerId(Long sellerId);

}

