package com.jaldimall.service.impl;

import com.jaldimall.config.JwtProvider;
import com.jaldimall.model.User;
import com.jaldimall.repository.UserRepository;
import com.jaldimall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        // check this method
        return this.findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user ==  null){
            throw new Exception("user not found");
        }

        return user;
    }
}
