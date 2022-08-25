package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.biz.dto.creditLoan.CreditLoanResult;
import com.ujutechnology.api8.biz.dto.deposit.DepositResult;
import com.ujutechnology.api8.biz.dto.mortgageLoan.MortgageResult;
import com.ujutechnology.api8.biz.dto.rentHouseLoan.RentHouseResult;
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

    @GetMapping("/depositProduct")
    public void DepositProductsApi() {
        DepositResult result = webClient.baseUrl("http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json")
                .build()
                .get()
                .uri(uri -> {
                    URI build = uri.queryParams(temp()).build();
                    log.info("URI_TOSTRING = {}",build);
                    return build;
                })
                .retrieve()
                .bodyToMono(DepositResult.class)
                .block();
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
        temp.add("auth","");
        temp.add("topFinGrpNo","020000");
        temp.add("pageNo","1");
        return temp;
    }
}