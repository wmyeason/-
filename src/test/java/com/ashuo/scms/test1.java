package com.ashuo.scms;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class test1 {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void t1(){
        String a="111111";
        String s="$2a$10$pb69jzQGOzAUub6K3CPfKOv1E70D1Vw21B5QflM99AJ7yz4Lsl4Y6";


        System.out.println(passwordEncoder.matches(a,s));

    }
}
