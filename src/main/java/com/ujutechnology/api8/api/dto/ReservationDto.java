package com.ujutechnology.api8.api.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @author kei
 * @since 2022-08-26
 */
@Data
@Builder
public class ReservationDto {
    private String email;
    @NotNull
    private Long productId;
}
