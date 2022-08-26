package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.creditLoan.CreditLoanResult;
import com.ujutechnology.api8.api.dto.deposit.DepositBaseList;
import com.ujutechnology.api8.api.dto.deposit.DepositOuterWrapperResult;
import com.ujutechnology.api8.api.dto.deposit.DepositResult;
import com.ujutechnology.api8.api.dto.mortgageLoan.MortgageResult;
import com.ujutechnology.api8.api.dto.rentHouseLoan.RentHouseResult;
import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FinancialProductSearchController {
    private final WebClient.Builder webClient;
    private final ProductService productService;

    @GetMapping("/depositProduct")
    public void DepositProductsApi() {
        DepositResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json")
                .build()
                .get()
                .uri(uri -> uri.queryParams(temp()).build())
                .retrieve()
                .bodyToMono(DepositResult.class)
                .block();
        DepositOuterWrapperResult wrapperResult = result.getResult();
        for(DepositBaseList baseList : wrapperResult.getBaseList()){
 //           log.info("FINCONO={}, PRDNM={}, KORCONM={}, JOINMEMBER={}",baseList.getFin_co_no(),baseList.getFin_prdt_nm(),baseList.getKor_co_nm(),baseList.getJoin_member());
            Product product1= new Product();
            product1.setProductName(baseList.getFin_prdt_nm());
            product1.setProductRate(baseList.getMtrt_int());
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@" + product1.toString());
            Product product = baseList.ConvertToEntity();
//            log.info("ProductId = {}, ProductName = {} , ProductRate = {}",product.getId(), product.getProductName(),product.getProductRate());
            productService.save(product);


        }
//        productService.save(result.getResult().getBaseList());
    }

    @GetMapping("/MortgageLoanProduct")
    public void mortgageLoanProductsApi() {
        MortgageResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/mortgageLoanProductsSearch.json")
                .build()
                .get()
                .uri(uri -> {
                    URI build = uri.queryParams(temp()).build();
                    log.info("URI_TOSTRING = {}",build);
                    return build;
                })
                .retrieve()
                .bodyToMono(MortgageResult.class)
                .block();
    }

    @GetMapping("/RentHouseLoanProduct")
    public void RentHouseLoanProductsApi() {
        RentHouseResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/rentHouseLoanProductsSearch.json")
                .build()
                .get()
                .uri(uri -> {
                    URI build = uri.queryParams(temp()).build();
                    log.info("URI_TOSTRING = {}",build);
                    return build;
                })
                .retrieve()
                .bodyToMono(RentHouseResult.class)
                .block();
    }

    @GetMapping("/CreditLoanProduct")
    public void CreditLoanProductsApi() {
        CreditLoanResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/creditLoanProductsSearch.json")
                .build()
                .get()
                .uri(uri -> {
                    URI build = uri.queryParams(temp()).build();
                    log.info("URI_TOSTRING = {}",build);
                    return build;
                })
                .retrieve()
                .bodyToMono(CreditLoanResult.class)
                .block();
    }

    private MultiValueMap<String, String> temp(){
        LinkedMultiValueMap<String, String> temp = new LinkedMultiValueMap<>();
        temp.add("auth","c407c9271bf8cd469648c7e40a6de96e"); // api키
        temp.add("topFinGrpNo","020000"); // 은행 : 020000
        temp.add("pageNo","1");
        return temp;
    }
}