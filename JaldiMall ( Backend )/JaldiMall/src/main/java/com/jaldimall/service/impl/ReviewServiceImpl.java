package com.jaldimall.service.impl;

import com.jaldimall.model.Product;
import com.jaldimall.model.Review;
import com.jaldimall.model.User;
import com.jaldimall.repository.ReviewRepository;
import com.jaldimall.request.CreateReviewRequest;
import com.jaldimall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReviewText(req.getReviewText());
        review.setRating(req.getReviewRating());
        review.setProductImages(req.getProductImages());

        product.getReviews().add(review);

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.findByProductById(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {

    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(
                ()-> new Exception("review not found")
        );
    }
}
