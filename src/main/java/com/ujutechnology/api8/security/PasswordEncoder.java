package com.ujutechnology.api8.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author kei
 * @since 2022-09-01
 */

public class PasswordEncoder {
    public static String encode(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        return String.format("%064x", new BigInteger(1, md.digest()));
    }
}
