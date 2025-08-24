package com.jaldimall.utils;

import java.util.Random;

public class OtpUtil {
    public static String generateOtp(){
        int otpLength = 6;
        Random random= new Random();

        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
//        System.out.println("my otp is ......................."+ otp);
        return otp.toString();
    }
}
