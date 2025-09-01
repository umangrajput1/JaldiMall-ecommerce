package com.jaldimall.service;

import com.jaldimall.model.Deal;

import java.util.List;

public interface DealService {

    List<Deal> getDeals();
    Deal createDeal(Deal deal);
    Deal udpateDeal(Deal deal, Long id) throws Exception;
    void deleteDeal(Long id) throws Exception;
}
