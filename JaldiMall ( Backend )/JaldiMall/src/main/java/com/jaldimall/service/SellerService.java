package com.jaldimall.service;

import com.jaldimall.domain.AccountStatus;
import com.jaldimall.exception.SellerException;
import com.jaldimall.model.Seller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService {

    Seller getSellerProfile(String jwt) throws Exception;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerById(long id) throws SellerException;
    Seller getSellerByEmail(String email) throws Exception;
    List<Seller> getAllSellers(AccountStatus status);
    Seller updateSeller(long id, Seller seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    Seller verifyEmail(String email, String otp) throws Exception;
    Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws  Exception;
}
