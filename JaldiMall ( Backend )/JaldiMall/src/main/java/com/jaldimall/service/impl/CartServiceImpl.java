package com.jaldimall.service.impl;

import com.jaldimall.model.Cart;
import com.jaldimall.model.CartItem;
import com.jaldimall.model.Product;
import com.jaldimall.model.User;
import com.jaldimall.repository.CartItemRepository;
import com.jaldimall.repository.CartRepository;
import com.jaldimall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addCardItem(User user, Product product, String size, int quantity) {
        Cart cart = findUserCart(user);

        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart, product, size);
        if (isPresent == null){
            CartItem  cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            int totalPrice  = quantity * product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity*product.getMrpPrice());

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);
            return  cartItemRepository.save(cartItem);
        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());

        int totalPrice = 0;
        int totalDiscountPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem : cart.getCartItems()) {
            int mrpPrice = (cartItem.getMrpPrice() != null) ? cartItem.getMrpPrice() : 0;
            int sellingPrice = (cartItem.getSellingPrice() != null) ? cartItem.getSellingPrice() : 0;
            int quantity = (cartItem.getQuantity() != 0) ? cartItem.getQuantity() : 0;

            // multiply with quantity
            totalPrice += mrpPrice * quantity;
            totalDiscountPrice += sellingPrice * quantity;
            totalItem += quantity;
        }

        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalSellingPrice(totalDiscountPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountPrice));

        return cart;
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if (mrpPrice==0){
            return 0;
        }
        double discount = mrpPrice-sellingPrice;
        double discountPercentage = (discount/mrpPrice) * 100;
        return (int)(discountPercentage);
    }
}
