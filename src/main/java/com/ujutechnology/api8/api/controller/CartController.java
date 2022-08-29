package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.CartDto;
import com.ujutechnology.api8.biz.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart")
    public void addCart(CartDto cartDto) {
        log.info(cartDto.toString());
        cartService.addCart(cartDto);
    }

    @DeleteMapping("/cart")
    public void deleteCart(CartDto cartDto) {
        log.info(cartDto.toString());
        cartService.deleteCart(cartDto);
    }
}
