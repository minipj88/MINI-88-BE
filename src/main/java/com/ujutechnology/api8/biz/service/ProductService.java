package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getRates(double rate) {
        return productRepository.findByProductRate(rate);
    }

    public Product getTags(String tag) {
        return productRepository.findByProductTag(tag);
    }
}
