//package org.example.calcutask.Util;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//// This utility class provides methods for hashing and verifying passwords using BCrypt.
//public class PasswordUtil {
//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    public static String hashPassword(String rawPassword) {
//        return encoder.encode(rawPassword);
//    }
//
//    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
//        return encoder.matches(rawPassword, hashedPassword);
//    }
//}