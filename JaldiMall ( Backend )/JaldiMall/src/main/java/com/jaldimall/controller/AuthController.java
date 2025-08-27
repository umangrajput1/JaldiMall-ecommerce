package com.jaldimall.controller;

import com.jaldimall.domain.USER_ROLE;
import com.jaldimall.model.User;
import com.jaldimall.model.VerificationCode;
import com.jaldimall.repository.UserRepository;
import com.jaldimall.request.LoginOtpRequest;
import com.jaldimall.request.LoginRequest;
import com.jaldimall.response.ApiResponse;
import com.jaldimall.response.AuthResponse;
import com.jaldimall.response.SignupRequest;
import com.jaldimall.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest request) throws Exception {

        String jwt=authService.createUser(request);

        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("register success");
        res.setRole(USER_ROLE.ROLE_CUSTOMER);


        return ResponseEntity.ok(res);
    }

    @PostMapping("/sent/login-sign-otp")
    public ResponseEntity<ApiResponse> sentOtpHandler(
            @RequestBody LoginOtpRequest request) throws Exception {

        authService.sentLoginOtp(request.getEmail(), request.getRole());

        ApiResponse res=new ApiResponse();

        res.setMassage("otp sent successfully");

        return ResponseEntity.ok(res);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginRequest request) throws Exception {

        AuthResponse authResponse = authService.signing(request);


        return ResponseEntity.ok(authResponse);
    }

}
