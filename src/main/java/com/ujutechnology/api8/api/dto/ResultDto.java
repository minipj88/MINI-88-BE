package com.ujutechnology.api8.api.dto;

import lombok.*;

/**
 * @author kei
 * @since 2022-08-25
 */
@Data
public class ResultDto<T> {
    boolean result;
    private T data;
}
