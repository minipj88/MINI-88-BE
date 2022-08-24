package com.ujutechnology.api8.biz.repository;

import com.ujutechnology.api8.biz.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductRate(double productRate);
    Product findByProductTag(String productTag);
}
