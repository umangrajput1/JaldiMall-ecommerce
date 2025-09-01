package com.jaldimall.service.impl;

import com.jaldimall.domain.HomeCategorySection;
import com.jaldimall.model.Deal;
import com.jaldimall.model.Home;
import com.jaldimall.model.HomeCategory;
import com.jaldimall.repository.DealRepository;
import com.jaldimall.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final DealRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {

        List<HomeCategory> gridCategories = allCategories.stream()
                .filter( category ->
                        category.getSection() == HomeCategorySection.GRID)
                .collect(Collectors.toList());

        List<HomeCategory> shopByCategories = allCategories.stream()
                .filter( category ->
                        category.getSection() == HomeCategorySection.SHOP_BY_CATEGORIES)
                .collect(Collectors.toList());

        List<HomeCategory> electricCategories = allCategories.stream()
                .filter( category ->
                        category.getSection() == HomeCategorySection.ELECTRIC_CATEGORIES)
                .collect(Collectors.toList());

        List<HomeCategory> dealCategories = allCategories.stream()
                .filter( category ->
                        category.getSection() == HomeCategorySection.DEALS)
                .collect(Collectors.toList());

        List<Deal> createDeals = new ArrayList<>();

        if (dealRepository.findAll().isEmpty()){
            List<Deal> deals = allCategories.stream()
                    .filter( category ->
                            category.getSection() == HomeCategorySection.DEALS)
                    .map( category -> new Deal(null, 10, category))
                    .collect(Collectors.toList());
            createDeals = dealRepository.saveAll(deals);
        }else {
            createDeals = dealRepository.findAll();
        }

        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectricCategories(electricCategories);
        home.setDeals(createDeals);
        home.setDealCategories(dealCategories);

        return home;
    }
}
