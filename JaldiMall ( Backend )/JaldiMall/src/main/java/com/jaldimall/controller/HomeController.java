package com.jaldimall.controller;

import com.jaldimall.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    ApiResponse apiResponse;

    @RequestMapping("/home")
    public ApiResponse HomeHandler(){
        apiResponse.setMassage("Welcome, JaldiMall e-commerce website");
        return apiResponse;
    }
}
