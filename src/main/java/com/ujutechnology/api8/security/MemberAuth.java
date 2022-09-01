package com.ujutechnology.api8.security;

import lombok.Data;

/**
 * @author kei
 * @since 2022-08-31
 */
@Data
public class MemberAuth {
    private String email;
    private String token;
}
