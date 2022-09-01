package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }

    @Transactional
    public Page<Product> getProduct(String productType, Pageable pageable) {
        return productRepository.findAllByProductType(productType, pageable);
    }

}
