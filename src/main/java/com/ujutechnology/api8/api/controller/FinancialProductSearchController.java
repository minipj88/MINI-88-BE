package com.ujutechnology.api8.api.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ujutechnology.api8.api.dto.ProductBaseList;
import com.ujutechnology.api8.api.dto.ProductOptionList;
import com.ujutechnology.api8.api.dto.ProductResult;
import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.domain.TodoSpecification;
import com.ujutechnology.api8.biz.repository.ProductRepository;
import com.ujutechnology.api8.biz.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FinancialProductSearchController {
    private final WebClient.Builder webClient;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private static int count=0;

    @Value("${external.api.key}")
    private String apiKey;

    @EventListener(ApplicationReadyEvent.class)
    public void createProduct() throws IOException {

        List<String> jobs = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        List<Integer> maxAmounts = new ArrayList<>();
        for(int i=0; i<jsonData().size(); i++){
            JsonObject jsonObject = (JsonObject) jsonData().get(i);

            jobs.add(jsonObject.get("job").getAsString());
            ages.add(jsonObject.get("age").getAsInt());
            maxAmounts.add(jsonObject.get("amount").getAsInt());
        }

        mortgageLoanProductsApi(jobs, ages, maxAmounts);
        rentHouseLoanProductsApi(jobs, ages, maxAmounts);
        creditLoanProductsApi(jobs, ages, maxAmounts);
    }

    public JsonArray jsonData() throws IOException {
        ClassPathResource resource = new ClassPathResource("product.json");
        JsonArray jsonArray = (JsonArray) JsonParser.parseReader(new InputStreamReader(resource.getInputStream()));
        return jsonArray;
    }

    public void mortgageLoanProductsApi(List<String> jobs, List<Integer> ages, List<Integer> maxAmounts) {
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
            double minRate = result.getResult().getOptionList().get(i).getLendRateMin();
            double maxRate = result.getResult().getOptionList().get(i).getLendRateMax();
            String image = ImageByBank(result, i);
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"전세", minRate, maxRate, jobs.get(count), ages.get(count), maxAmounts.get(count), image);
            productService.save(product);
            count++;
        }
    }

    public void rentHouseLoanProductsApi(List<String> jobs, List<Integer> ages, List<Integer> maxAmounts) {
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
            ProductOptionList option = result.getResult().getOptionList().get(i);
            double minRate;
            double maxRate;
            if(option.getLendRateMin()!=null)
                 minRate = result.getResult().getOptionList().get(i).getLendRateMin();
            else
                minRate = 0.00;
            if(option.getLendRateMax()!=null)
                maxRate = result.getResult().getOptionList().get(i).getLendRateMax();
            else
                maxRate = 0.00;
            String image = ImageByBank(result, i);
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"주택", minRate, maxRate, jobs.get(count), ages.get(count), maxAmounts.get(count), image);
            productService.save(product);
            count++;
        }
    }

    public void creditLoanProductsApi(List<String> jobs, List<Integer> ages, List<Integer> maxAmounts) {
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
                ProductOptionList option = result.getResult().getOptionList().get(i);
                List<String> gradeList = new ArrayList(Arrays.asList(option.getCrdtGrad_1(), option.getCrdtGrad_4(), option.getCrdtGrad_5(), option.getCrdtGrad_6(), option.getCrdtGrad_10(), option.getCrdtGrad_11(), option.getCrdtGrad_13()));
                while(gradeList.remove(null)) {
                }
                int num =gradeList.size();
                Collections.sort(gradeList);
                double minRate = Double.parseDouble(gradeList.get(0));
                double maxRate = Double.parseDouble(gradeList.get(num-1));
                String image = ImageByBank(result, i);
                Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"신용", minRate, maxRate, jobs.get(count), ages.get(count), maxAmounts.get(count), image);
                productService.save(product);
                count++;
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
            @RequestParam(required = false) String financialCompanyName,
            @RequestParam(required = false) String productName
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
    public Page getProductList(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page page = productRepository.findAll(pageable);
        return page;
    }

    @GetMapping("/classification")
    public Page classifyingProduct(@RequestBody String productType, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Page page = productService.getProduct(productType, pageable);
        return page;
    }

    private MultiValueMap<String, String> temp(){

        LinkedMultiValueMap<String, String> temp = new LinkedMultiValueMap<>();
        temp.add("auth",apiKey); // api키
        temp.add("topFinGrpNo","020000"); // 은행 : 020000
        temp.add("pageNo","1");
        return temp;
    }

    private Product convertToEntity(ProductBaseList baseList, ProductOptionList optionList, String productType, double minRate, double maxRate, String jobs, int ages, int maxAmounts, String image){
        return Product.builder()
                .financialCompanyName(baseList.getKorCoNm())
                .productName(baseList.getFinPrdtNm())
                .productNumber(baseList.getFinPrdtCd())
                .cbName(baseList.getCbName())
                .joinWay(baseList.getJoinWay())
                .age(ages)
                .minRate(minRate)
                .maxRate(maxRate)
                .job(jobs)
                .productType(productType)
                .maxAmount(maxAmounts)
                .image(image)
                .build();
    }

    private String ImageByBank(ProductResult result, int i) {
        switch (result.getResult().getBaseList().get(i).getKorCoNm()){
            case "부산은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/ccbd1c20-39b6-42e8-90ca-b6a52d314bef-%EB%B6%80%EC%82%B0%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "대구은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/7be7f832-17f5-4bb2-8100-8911a83a8755-%EB%8C%80%EA%B5%AC%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "하나은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/4edc0b4f-c40b-4e80-b5fe-4a3ac5acf546-%ED%95%98%EB%82%98%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "우리은행" :
                return  "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/caef28b7-6a52-427d-9ac4-8b0558560b3a-%EC%9A%B0%EB%A6%AC%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "한국스탠다드차타드은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/c1616212-2d4d-40d5-b331-48f8e8bf13fe-%EC%8A%A4%ED%83%A0%EB%8B%A4%EB%93%9C%EB%A1%9C%EA%B3%A0.jpg";
            case "광주은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/b13b4806-5909-457a-a80e-637ed38fa5b0-%EA%B4%91%EC%A3%BC%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "제주은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/1b896104-ee50-451c-90cc-332ad64c4720-%EC%A0%9C%EC%A3%BC%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "전북은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/5de79600-79f5-43cc-b6cb-f8d7a3b82426-%EC%A0%84%EB%B6%81%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "경남은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/c13e7a15-c777-475f-b839-35f9192cb36d-%EA%B2%BD%EB%82%A8%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "중소기업은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/9e57dcc5-35a2-4c60-8c90-6a29815fa487-%EC%A4%91%EC%86%8C%EA%B8%B0%EC%97%85%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "한국산업은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/56355fc2-852f-4d3d-afbd-3320b03766b5-%ED%95%9C%EA%B5%AD%EC%82%B0%EC%97%85%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "국민은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/96c1a48d-3c51-4ae6-b568-8101391ed772-%EA%B5%AD%EB%AF%BC%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "신한은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/5909c395-7531-450b-8998-318fc57539d7-%EC%8B%A0%ED%95%9C%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "농협은행주식회사" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/ce77a5b5-ba88-4b22-9f5f-b07ac224dd2f-%EB%86%8D%ED%98%91%EB%A1%9C%EA%B3%A0.jpg";
            case "수협은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/01867fda-1ac6-4d88-a301-7dcb186868ec-%EC%88%98%ED%98%91%EB%A1%9C%EA%B3%A0.jpg";
            case "한국씨티은행" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/71634745-1530-4908-9f3c-654a354f55d2-%ED%95%9C%EA%B5%AD%EC%94%A8%ED%8B%B0%EC%9D%80%ED%96%89%EB%A1%9C%EA%B3%A0.jpg";
            case "주식회사 케이뱅크" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/d013fb36-cf36-43a6-b174-9a880dd7a86a-%EC%BC%80%EC%9D%B4%EB%B1%85%ED%81%AC%EB%A1%9C%EA%B3%A0.jpg";
            case "주식회사 카카오뱅크" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/b1dc88e6-0f65-4e82-899f-ded47f2f0eb1-%EC%B9%B4%EC%B9%B4%EC%98%A4%EB%B1%85%ED%81%AC%EB%A1%9C%EA%B3%A0.png";
            case "토스뱅크 주식회사" :
                return "https://8tak8takbucket.s3.ap-northeast-2.amazonaws.com/b7cd7f54-dade-43d2-9866-8702cfe32887-%ED%86%A0%EC%8A%A4%EB%B1%85%ED%81%AC%EB%A1%9C%EA%B3%A0.png";
        }
        return "";
    }
}