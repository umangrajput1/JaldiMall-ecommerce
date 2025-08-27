package com.jaldimall.service;

import com.jaldimall.model.User;

public interface UserService {

    User findByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;

}
