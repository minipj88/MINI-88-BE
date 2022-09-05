package com.ujutechnology.api8.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;

/**
 * @author kei
 * @since 2022-08-30
 */

public class JwtSimpleTest {

    private void printToken(String token) {
        String[] tokens = token.split("\\.");
        System.out.println("header: " + new String(Base64.getDecoder().decode(tokens[0])));
        System.out.println("body: " + new String(Base64.getDecoder().decode(tokens[1])));
    }

    @DisplayName("1. java-jwt를 이용한 토큰 테스트")
    @Test
    void test_1() {
        byte[] SEC_KEY = DatatypeConverter.parseBase64Binary("backend");

        String token = JWT.create()
                .withClaim("name", "backend")
                .withClaim("point", 800)
                .sign(Algorithm.HMAC256(SEC_KEY));

        System.out.println(token);
        printToken(token);

        DecodedJWT verified = JWT.require(Algorithm.HMAC256(SEC_KEY)).build().verify(token);
        System.out.println(verified.getClaims());
    }

    @DisplayName("2. 만료시간 테스트")
    @Test
    void test_2() throws InterruptedException {
        final Algorithm AL = Algorithm.HMAC256("backend");
        String token = JWT.create().withSubject("fintech")
                .withNotBefore(new Date(System.currentTimeMillis() + 1000)) // 1초후에 사용가능
                .withExpiresAt(new Date(System.currentTimeMillis() + 3000)) // 3초후에 만료
                .sign(AL);
        Thread.sleep(2000);
        try {
            DecodedJWT verify = JWT.require(AL).build().verify(token);
            System.out.println(verify.getClaims());
        } catch (Exception e) {
            System.out.println("유효하지 않은 토큰입니다...");
            DecodedJWT decode = JWT.decode(token);
            System.out.println(decode.getClaims());
        }
    }


}
