package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.api.dto.CartDto;
import com.ujutechnology.api8.biz.domain.Cart;
import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartRepository cartRepository;

    public void addCart(CartDto cartDto) {
        Cart cart = Cart.builder()
                .memberEmail(cartDto.getEmail())
                .product(Product.builder().id(cartDto.getProductId()).build())
                .build();
        cartRepository.save(cart);
    }

    public void deleteCart(CartDto cartDto) {
        cartRepository.deleteByMemberEmailAndProductId(cartDto.getEmail(), cartDto.getProductId());
    }
    public void deleteCartList(String email) {
        cartRepository.deleteByMemberEmail(email);
    }
    public List<Cart> getCartList(String email) {
        return cartRepository.findByMemberEmail(email);
    }

}
