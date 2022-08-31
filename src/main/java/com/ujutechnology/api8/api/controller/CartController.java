package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.CartDto;
import com.ujutechnology.api8.biz.domain.Cart;
import com.ujutechnology.api8.biz.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart")
    public void addCart(@RequestBody CartDto cartDto) {
        log.info("addCart>>> "+cartDto.toString());
        cartService.addCart(cartDto);
    }

    @DeleteMapping("/cart")
    public void deleteCart(@RequestBody CartDto cartDto) {
        log.info("deleteCart>>> "+ cartDto.toString());
        cartService.deleteCart(cartDto);
    }

    @DeleteMapping("/carts")
    public void deleteCartList(@RequestBody String email) {
        log.info("deleteCartList>>> "+ email);
        cartService.deleteCartList(email);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getCartList(String email) {
        log.info("getCartList>>> "+email);
        return new ResponseEntity(cartService.getCartList(email), HttpStatus.OK);
    }
}
