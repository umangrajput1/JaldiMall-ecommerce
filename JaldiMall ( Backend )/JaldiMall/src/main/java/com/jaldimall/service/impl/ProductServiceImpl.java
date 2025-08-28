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
            category = categoryRepository.save(category);
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

    @Override
    public Product updateProduct(Long prodId, Product product) throws ProductException {
        Product product1 = findProductById(prodId);
        product.setId(prodId);

        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long prodId) throws ProductException {

        return productRepository.findById(prodId).orElseThrow(
                ()-> new ProductException("product not found with id "+prodId)
        );

    }

    @Override
    public List<Product> searchProducts() {
        return List.of();
    }

    @Override
    public Page<Product> getAllProducts(String category,
                                        String brand, String colors, String sizes,
                                        Integer minPrice, Integer maxPrice,
                                        String minDiscount, String sort,
                                        String stock, Integer pageNumber) {

        Specification<Product> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null && !category.isEmpty()) {
                Join<Product, Category> categoryJoin = root.join("category");
                predicates.add(cb.equal(categoryJoin.get("categoryId"), Long.valueOf(category)));
            }

            if (colors != null && !colors.isEmpty()) {
                predicates.add(cb.like(root.get("colors"), "%" + colors + "%"));
            }

            if (sizes != null && !sizes.isEmpty()) {
                predicates.add(cb.like(root.get("sizes"), "%" + sizes + "%"));
            }

            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("sellingPrice"), maxPrice));
            }

            if (minDiscount != null && !minDiscount.isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(
                        root.get("discountPercentage"), Integer.parseInt(minDiscount)
                ));
            }

            if (stock != null && !stock.isEmpty()) {
                predicates.add(cb.equal(root.get("stock"), Integer.valueOf(stock)));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
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

//        return productRepository.findAll(specification, pageable);
        return null;
    }


    @Override
    public List<Product> getProductBySellerId(Long sellerId) {
        return List.of();
    }
}
