package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.api.dto.deposit.DepositBaseList;
import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }
}
