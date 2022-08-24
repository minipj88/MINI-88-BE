package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.biz.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

}
