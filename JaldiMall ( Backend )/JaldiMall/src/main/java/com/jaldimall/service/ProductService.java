package com.jaldimall.service;

import com.jaldimall.exception.ProductException;
import com.jaldimall.model.Product;
import com.jaldimall.model.Seller;
import com.jaldimall.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import javax.sound.sampled.Port;
import java.util.List;

public interface ProductService {

    public Product createProduct(CreateProductRequest req, Seller seller);
    public void deleteProduct(Long prodId) throws ProductException;
    public Product updateProduct(Long prodId, Product product) throws ProductException;
    Product findProductById(Long prodId) throws ProductException;
    List<Product> searchProducts();
    public Page<Product> getAllProducts(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            String minDiscount,
            String sort,
            String stock,
            Integer pageNumber

    );
    List<Product> getProductBySellerId(Long sellerId);

}

