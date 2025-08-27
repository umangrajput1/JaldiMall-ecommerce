package com.jaldimall.service;

import com.jaldimall.domain.USER_ROLE;
import com.jaldimall.model.User;
import com.jaldimall.request.LoginRequest;
import com.jaldimall.response.AuthResponse;
import com.jaldimall.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email, USER_ROLE role) throws Exception;

    String createUser(SignupRequest req) throws Exception;

    AuthResponse signing(LoginRequest req);
}
