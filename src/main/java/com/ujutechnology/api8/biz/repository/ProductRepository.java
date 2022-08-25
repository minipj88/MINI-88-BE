package com.ujutechnology.api8.biz.repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductRate(double productRate);
    List<Product> findByProductTag(String productTag);

}
