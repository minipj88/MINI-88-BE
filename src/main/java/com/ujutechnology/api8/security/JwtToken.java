package com.ujutechnology.api8.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author kei
 * @since 2022-08-31
 */

public class JwtToken {
    private final static Algorithm AL = Algorithm.HMAC256("backend");
    private final static long exp = 1000L * 60 * 60 * 24 * 7;
    public static String Encode(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + exp))
                .sign(AL);
    }

    public static String Decode(String token) {
        DecodedJWT verify = JWT.require(AL).build().verify(token);
        return verify.getSubject();
    }
}
