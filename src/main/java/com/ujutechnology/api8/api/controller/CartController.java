package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.CartDto;
import com.ujutechnology.api8.biz.domain.Cart;
import com.ujutechnology.api8.biz.service.CartService;
import com.ujutechnology.api8.security.MemberAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    @PostMapping("/cart")
    public void addCart(@RequestBody CartDto cartDto, HttpSession session) {
        cartDto.setEmail(
                ((MemberAuth)session.getAttribute("auth")).getEmail()
        );
        log.info("addCart>>> "+ cartDto.toString());
        cartService.addCart(cartDto);
    }

    @DeleteMapping("/cart")
    public void deleteCart(@RequestBody CartDto cartDto, HttpSession session) {
        cartDto.setEmail(
                ((MemberAuth)session.getAttribute("auth")).getEmail()
        );        log.info("deleteCart>>> "+ cartDto.toString());
        cartService.deleteCart(cartDto);
    }

    @DeleteMapping("/carts")
    public void deleteCartList(HttpSession session) {
        String email = ((MemberAuth)session.getAttribute("auth")).getEmail();
        log.info("deleteCartList>>> "+ email);
        cartService.deleteCartList(email);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getCartList(HttpSession session) {
        String email = ((MemberAuth)session.getAttribute("auth")).getEmail();
        log.info("getCartList>>> "+email);
        return ResponseEntity.ok(cartService.getCartList(email));
    }
}
