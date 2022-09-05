package com.ujutechnology.api8.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author kei
 * @since 2022-09-01
 */

public class PasswordEncoderTest {
    @DisplayName("1. 패스워드 암호화  테스트")
    @Test
    void test_1() throws Exception {
        String encode1 = PasswordEncoder.encode("1234");
        String encode2 = PasswordEncoder.encode("1234");
        assertTrue(encode1.equals(encode2));
    }

}
