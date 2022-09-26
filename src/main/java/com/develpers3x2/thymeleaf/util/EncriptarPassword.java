package com.develpers3x2.thymeleaf.util;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {

    public static void main(String[] args){
        var password = "123";
        System.out.println("Password " +password);
        System.out.println("Password encriptado " +encriptarPAssword(password));
    }

    private static String encriptarPAssword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
