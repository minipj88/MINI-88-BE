package com.ujutechnology.api8.biz.repository;

import com.ujutechnology.api8.biz.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    void deleteByMemberEmail(String email);
    void deleteByMemberEmailAndProductId(String email, Long productId);

    List<Cart> findByMemberEmail(String email);

}

