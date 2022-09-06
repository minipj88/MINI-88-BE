package com.ujutechnology.api8.api.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @author kei
 * @since 2022-08-25
 */
@Data
@Builder
public class LoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
