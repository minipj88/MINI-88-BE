package com.ujutechnology.api8.api.controller.dto;

import lombok.*;

/**
 * @author kei
 * @since 2022-08-25
 */
@Data
public class ResultDto<T> {
    private String message;
    private T data;
}