package com.jaldimall.service.impl;

import com.jaldimall.exception.ProductException;
import com.jaldimall.model.Category;
import com.jaldimall.model.Product;
import com.jaldimall.model.Seller;
import com.jaldimall.repository.CategoryRepository;
import com.jaldimall.repository.ProductRepository;
import com.jaldimall.request.CreateProductRequest;
import com.jaldimall.service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest req, Seller seller) {

        Category category1 = categoryRepository.findByCategoryId(req.getCategory());

        if( category1 ==null){
            Category category=new Category();
            category.setCategoryId(req.getCategory());
            category.setLevel(1);
            category1 = categoryRepository.save(category);
        }

        Category category2 = categoryRepository.findByCategoryId(req.getCategory2());

        if (category2==null){
            Category category=new Category();
            category.setCategoryId(req.getCategory2());
            category.setLevel(2);
            category.setParentCategory(category1);
            category2 = categoryRepository.save(category);
        }

        Category category3 = categoryRepository.findByCategoryId(req.getCategory3());
        if (category3==null){
            Category category = new Category();
            category.setCategoryId(req.getCategory3());
            category.setLevel(3);
            category.setParentCategory(category2);
            categoryRepository.save(category);
        }

        int discountPercentage = calculateDiscountPercentage(req.getMrpPrice(), req.getSellingPrice());

        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(category3);
        product.setDescription(req.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setSellingPrice(req.getSellingPrice());
        product.setImages(req.getImages());
        product.setMrpPrice(req.getMrpPrice());
        product.setSizes(req.getSizes());
        product.setDiscountPercent(discountPercentage);

        return productRepository.save(product);
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {

            if (mrpPrice==0){
                throw new IllegalArgumentException("Actual price must be grater than o");
            }
            double discount = mrpPrice-sellingPrice;
            double discountPercentage = (discount/mrpPrice) * 100;
            return (int)(discountPercentage);
    }

    @Override
    public void deleteProduct(Long prodId) throws ProductException {
        Product product = findProductById(prodId);
        productRepository.delete(product);

    }

    public Product updateProduct(Long prodId, Product updatedProduct) throws ProductException {
        Product existingProduct = findProductById(prodId);

        if (updatedProduct.getTitle() != null) {
            existingProduct.setTitle(updatedProduct.getTitle());
        }
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }
        if (updatedProduct.getMrpPrice() != 0) {
            existingProduct.setMrpPrice(updatedProduct.getMrpPrice());
        }
        if (updatedProduct.getSellingPrice() != 0) {
            existingProduct.setSellingPrice(updatedProduct.getSellingPrice());
        }
        if (updatedProduct.getQuantity() != 0) {
            existingProduct.setQuantity(updatedProduct.getQuantity());
        }
        if (updatedProduct.getColor() != null) {
            existingProduct.setColor(updatedProduct.getColor());
        }
        if (updatedProduct.getImages() != null && !updatedProduct.getImages().isEmpty()) {
            existingProduct.setImages(updatedProduct.getImages());
        }
        if (updatedProduct.getSizes() != null) {
            existingProduct.setSizes(updatedProduct.getSizes());
        }
        if (updatedProduct.getCategory() != null) {
            existingProduct.setCategory(updatedProduct.getCategory());
        }

        return productRepository.save(existingProduct);
    }


    @Override
    public Product findProductById(Long prodId) throws ProductException {

        return productRepository.findById(prodId).orElseThrow(
                ()-> new ProductException("product not found with id "+prodId)
        );

    }

    @Override
    public List<Product> searchProducts(String query) {
        return productRepository.searchProduct(query);
    }

    @Override
    public Page<Product> getAllProducts(String category,
                                        String brand, String colors, String sizes,
                                        Integer minPrice, Integer maxPrice,
                                        Integer minDiscount, String sort,
                                        String stock, Integer pageNumber) {

        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null ) {
                Join<Product, Category> categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"),category));
            }

            if (colors != null && !colors.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("color"), colors));
            }

            if (sizes != null && !sizes.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("size"),sizes));
            }

            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
            }

            if (minDiscount != null ) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("discountPercentage"),minDiscount)
                );
            }

            if (stock != null ) {
                predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable;
        int pageSize = 10;

        if (sort != null && !sort.isEmpty()) {
            pageable = switch (sort) {
                case "price_low" -> PageRequest.of(pageNumber != null ? pageNumber : 0, pageSize,
                        Sort.by("sellingPrice").ascending());
                case "price_high" -> PageRequest.of(pageNumber != null ? pageNumber : 0, pageSize,
                        Sort.by("sellingPrice").descending());
                default -> PageRequest.of(pageNumber != null ? pageNumber : 0, pageSize, Sort.unsorted());
            };
        } else {
            pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, pageSize, Sort.unsorted());
        }

        return productRepository.findAll(specification, pageable);
    }


    @Override
    public List<Product> getProductBySellerId(Long sellerId) {
        return productRepository.findBySeller_Id(sellerId);
    }
}
