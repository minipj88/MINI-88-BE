package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.*;
import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.domain.TodoSpecification;
import com.ujutechnology.api8.biz.repository.ProductRepository;
import com.ujutechnology.api8.biz.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FinancialProductSearchController {
    private final WebClient.Builder webClient;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @GetMapping("/depositProduct")
    public void DepositProductsApi() {
        ProductResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json")
                .build()
                .get()
                .uri(uri -> uri.queryParams(temp()).build())
                .retrieve()
                .bodyToMono(ProductResult.class)
                .block();
        assert result != null;
        for (int i = 0; i < result.getResult().getBaseList().size(); i++) {
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i));
            productService.save(product);
        }
    }

    @GetMapping("/mortgageLoanProduct")
    public void mortgageLoanProductsApi() {
        ProductResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/mortgageLoanProductsSearch.json")
                .build()
                .get()
                .uri(uri -> {
                    URI build = uri.queryParams(temp()).build();
                    log.info("URI_TOSTRING = {}",build);
                    return build;
                })
                .retrieve()
                .bodyToMono(ProductResult.class)
                .block();
        assert result != null;
        for (int i = 0; i < result.getResult().getBaseList().size(); i++) {
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i));
            productService.save(product);
        }
    }

    @GetMapping("/rentHouseLoanProduct")
    public void RentHouseLoanProductsApi() {
        ProductResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/rentHouseLoanProductsSearch.json")
                .build()
                .get()
                .uri(uri -> {
                    URI build = uri.queryParams(temp()).build();
                    log.info("URI_TOSTRING = {}", build);
                    return build;
                })
                .retrieve()
                .bodyToMono(ProductResult.class)
                .block();
        assert result != null;
        for (int i = 0; i < result.getResult().getBaseList().size(); i++) {
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i));
            productService.save(product);
        }
    }

    @GetMapping("/creditLoanProduct")
    public void CreditLoanProductsApi() {
            ProductResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/creditLoanProductsSearch.json")
                    .build()
                    .get()
                    .uri(uri -> {
                        URI build = uri.queryParams(temp()).build();
                        log.info("URI_TOSTRING = {}", build);
                        return build;
                    })
                    .retrieve()
                    .bodyToMono(ProductResult.class)
                    .block();
            assert result != null;
            for (int i = 0; i < result.getResult().getBaseList().size(); i++) {
                Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i));
                productService.save(product);
            }
        }

    @GetMapping("/recommend")
    public List<Product> Recommendation(
            @RequestParam(required = false) String job,
            @RequestParam(required = false) Integer age
    ) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> null;

        if (age != null)
            spec = spec.and(TodoSpecification.withAge(age));
        if (job != null)
            spec = spec.and(TodoSpecification.withJob(job));

        return productRepository.findAll(spec);
    }

    @GetMapping("/searchLoan")
    public List<Product> searchLoan(
            @RequestParam String financialCompanyName,
            @RequestParam String productName
    ) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> null;

        if (financialCompanyName != null)
            spec = spec.and(TodoSpecification.withCompanyName(financialCompanyName));
        if (financialCompanyName != null)
            spec = spec.and(TodoSpecification.withProductName(productName));

        return productRepository.findAll(spec);
    }

    private MultiValueMap<String, String> temp(){
        LinkedMultiValueMap<String, String> temp = new LinkedMultiValueMap<>();
        temp.add("auth","c407c9271bf8cd469648c7e40a6de96e"); // api키
        temp.add("topFinGrpNo","020000"); // 은행 : 020000
        temp.add("pageNo","1");
        return temp;
    }

    public Product convertToEntity(ProductBaseList baseList, ProductOptionList optionList){ // Rate와 Age 추가예정
        return Product.builder()
                .financialCompanyName(baseList.getKorCoNm())
                .financialCompanyNumber(baseList.getKorCoNm())
                .productName(baseList.getFinPrdtNm())
                .productNumber(baseList.getFinPrdtCd())
                .cbName(baseList.getCbName())
                .joinWay(baseList.getJoinWay())
                .creditProductTypeName(optionList.getCrdtPrdtTypeNm())
                .age(20)
                .rate(5.2)
                .job("무직")
                .build();

    }
}