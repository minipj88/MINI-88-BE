package com.ujutechnology.api8.api.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @author kei
 * @since 2022-08-29
 */
@Data
@Builder
public class CartDto {
    private String email;
    @NotNull
    private Long productId;
}
