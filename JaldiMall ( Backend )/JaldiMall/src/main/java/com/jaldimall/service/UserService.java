package com.jaldimall.service;

import com.jaldimall.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;

}
