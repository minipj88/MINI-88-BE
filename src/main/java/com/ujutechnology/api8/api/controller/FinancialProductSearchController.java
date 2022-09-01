package com.ujutechnology.api8.api.controller;

import com.google.gson.Gson;
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
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.spring.web.json.Json;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FinancialProductSearchController {
    private final WebClient.Builder webClient;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private static int count=0;

    @EventListener(ApplicationReadyEvent.class)
    public void createProduct() throws IOException {
/*        depositProductsApi();*/

        List<String> jobs = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        for(int i=0; i<jsonData().size(); i++){
            JsonObject jsonObject = (JsonObject) jsonData().get(i);

            jobs.add(jsonObject.get("job").getAsString());
            ages.add(jsonObject.get("age").getAsInt());
        }

        mortgageLoanProductsApi(jobs, ages);
        rentHouseLoanProductsApi(jobs, ages);
        creditLoanProductsApi(jobs, ages);
    }

    public JsonArray jsonData() throws IOException {
        ClassPathResource resource = new ClassPathResource("product.json");
        JsonArray jsonArray = (JsonArray) JsonParser.parseReader(new InputStreamReader(resource.getInputStream()));
        return jsonArray;
    }

/*    @GetMapping("/depositProduct")
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
    }*/

    @GetMapping("/mortgageLoanProduct")
    public void mortgageLoanProductsApi(List<String> jobs, List<Integer> ages) {
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
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"전세", minRate, maxRate, jobs.get(count), ages.get(count));
            productService.save(product);
            count++;
        }
    }

    @GetMapping("/rentHouseLoanProduct")
    public void rentHouseLoanProductsApi(List<String> jobs, List<Integer> ages) {
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
            Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"주택", minRate, maxRate, jobs.get(count), ages.get(count));
            productService.save(product);
            count++;
        }
    }

    @GetMapping("/creditLoanProduct")
    public void creditLoanProductsApi(List<String> jobs, List<Integer> ages) {
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

                Product product = convertToEntity(result.getResult().getBaseList().get(i), result.getResult().getOptionList().get(i),"신용", minRate, maxRate, jobs.get(count), ages.get(count));
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
        temp.add("auth","c407c9271bf8cd469648c7e40a6de96e"); // api키
        temp.add("topFinGrpNo","020000"); // 은행 : 020000
        temp.add("pageNo","1");
        return temp;
    }

    private Product convertToEntity(ProductBaseList baseList, ProductOptionList optionList, String productType, double minRate, double maxRate, String jobs, int ages){ // Rate와 Age 추가예정
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
                .build();
    }
}