package com.ujutechnology.api8.biz.repository;

import com.ujutechnology.api8.biz.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    void deleteByMemberEmailAndProductId(String email, Long productId);
}

