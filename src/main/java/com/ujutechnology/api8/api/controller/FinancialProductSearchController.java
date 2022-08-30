package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.ProductBaseList;
import com.ujutechnology.api8.api.dto.ProductOptionList;
import com.ujutechnology.api8.api.dto.ProductResult;
import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.domain.TodoSpecification;
import com.ujutechnology.api8.biz.repository.ProductRepository;
import com.ujutechnology.api8.biz.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FinancialProductSearchController {
    private final WebClient.Builder webClient;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void createProduct() {
        depositProductsApi();
        mortgageLoanProductsApi();
        rentHouseLoanProductsApi();
        creditLoanProductsApi();
    }

    @GetMapping("/depositProduct")
    public void depositProductsApi() {
        ProductResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json")
                .build()
                .get()
                .uri(uri -> uri.queryParams(temp()).build())
                .retrieve()
                .bodyToMono(ProductResult.class)
                .block();
        assert result != null;
        for (int i = 0; i < result.getResult().getBaseList().size(); i++) {
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"적금");
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
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"전세");
            productService.save(product);
        }
    }

    @GetMapping("/rentHouseLoanProduct")
    public void rentHouseLoanProductsApi() {
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
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"주택");
            productService.save(product);
        }
    }

    @GetMapping("/creditLoanProduct")
    public void creditLoanProductsApi() {
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
                Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"신용");
                productService.save(product);
            }
        }

    @GetMapping("/recommend")
    public List<Product> Recommendation(
            @RequestParam(required = false) String job,
            @RequestParam(required = false) Integer age
    ) {
        age = age/10;
        age = age*10;
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

        List<Product> productList = productRepository.findAll(spec);
        return productList;
//         return productRepository.findAll(spec);
    }

    @GetMapping("/details/{id}")
    public Product getProductDetails(@PathVariable long id){
        Product product = productRepository.findById(id).get();
        return product;
    }

    @GetMapping("/main")
    public List<Product> getProductList(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @GetMapping("/classification")
    public List<Product> classifyingProduct(String productType){
        return productService.getProduct(productType);
    }

    private MultiValueMap<String, String> temp(){
        LinkedMultiValueMap<String, String> temp = new LinkedMultiValueMap<>();
        temp.add("auth","c407c9271bf8cd469648c7e40a6de96e"); // api키
        temp.add("topFinGrpNo","020000"); // 은행 : 020000
        temp.add("pageNo","1");
        return temp;
    }

    private Product convertToEntity(ProductBaseList baseList, ProductOptionList optionList, String productType){ // Rate와 Age 추가예정
        return Product.builder()
                .financialCompanyName(baseList.getKorCoNm())
                .productName(baseList.getFinPrdtNm())
                .productNumber(baseList.getFinPrdtCd())
                .cbName(baseList.getCbName())
                .joinWay(baseList.getJoinWay())
                .age(20)
                .rate(5.2)
                .job("무직")
                .productType(productType)
                .build();

    }
}