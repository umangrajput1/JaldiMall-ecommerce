package com.jaldimall.service;

import com.jaldimall.model.Home;
import com.jaldimall.model.HomeCategory;

import java.util.List;

public interface HomeService {

    Home createHomePageData(List<HomeCategory> allCategories);
}
