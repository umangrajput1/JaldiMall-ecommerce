package com.jaldimall.service;


import com.jaldimall.model.Order;
import com.jaldimall.model.Seller;
import com.jaldimall.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySellerId(Seller seller);
    List<Transaction> getAllTransactions();
}
