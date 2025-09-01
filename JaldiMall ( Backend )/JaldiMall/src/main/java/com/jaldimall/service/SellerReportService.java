package com.jaldimall.service;

import com.jaldimall.model.Seller;
import com.jaldimall.model.SellerReport;
import org.springframework.stereotype.Service;

@Service
public interface SellerReportService {

    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
