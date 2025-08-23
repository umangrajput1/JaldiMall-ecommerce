package com.jaldimall.service;

import com.jaldimall.response.SignupRequest;

public interface AuthService {

    String createUser(SignupRequest req);

}
