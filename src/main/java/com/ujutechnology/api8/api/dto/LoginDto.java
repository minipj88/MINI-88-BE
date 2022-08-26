package com.ujutechnology.api8.api.dto;

import lombok.Data;

/**
 * @author kei
 * @since 2022-08-25
 */
@Data
public class LoginDto {
    private String email;
    private String password;
}
