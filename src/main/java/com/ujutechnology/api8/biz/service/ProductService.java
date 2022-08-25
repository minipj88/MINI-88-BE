package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getRates(double rate) {
        return productRepository.findByProductRate(rate);
    }

    public List<Product> getTags(String tag) {
        return productRepository.findByProductTag(tag);
    }
}
