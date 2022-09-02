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
    @Value("${bank.image.KakaoBank}")
    private String Kakao;
    @Value("${bank.image.KBBank}")
    private String KB;
    @Value("${bank.image.KBank}")
    private String K;
    @Value("${bank.image.CTBank}")
    private String CT;
    @Value("${bank.image.NHBank}")
    private String NH;
    @Value("${bank.image.SHBank}")
    private String SH;
    @Value("${bank.image.ShinhanBank}")
    private String Shinhan;
    @Value("${bank.image.KoreaIndustryBank}")
    private String KoreaIndustry;
    @Value("${bank.image.IBKBank}")
    private String IBK;
    @Value("${bank.image.GyeongNamBank}")
    private String GyeongNam;
    @Value("${bank.image.JBBank}")
    private String JB;
    @Value("${bank.image.JejuBank}")
    private String Jeju;
    @Value("${bank.image.GwangjuBank}")
    private String Gwangju;
    @Value("${bank.image.SCBank}")
    private String SC;
    @Value("${bank.image.WooriBank}")
    private String Woori;
    @Value("${bank.image.BusanBank}")
    private String Busan;
    @Value("${bank.image.DaeguBank}")
    private String Daegu;
    @Value("${bank.image.HanaBank}")
    private String Hana;


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
        String image = "";
        switch (result.getResult().getBaseList().get(i).getKorCoNm()){
            case "부산은행" :
                image = Busan;
                break;
            case "대구은행" :
                image = Daegu;
                break;
            case "하나은행" :
                image = Hana;
                break;
            case "우리은행" :
                image = Woori;
                break;
            case "한국스탠다드차타드은행" :
                image = SC;
                break;
            case "광주은행" :
                image = Gwangju;
                break;
            case "제주은행" :
                image = Jeju;
                break;
            case "전북은행" :
                image = JB;
                break;
            case "경남은행" :
                image = GyeongNam;
                break;
            case "중소기업은행" :
                image = IBK;
                break;
            case "한국산업은행" :
                image = KoreaIndustry;
                break;
            case "국민은행" :
                image = KB;
                break;
            case "신한은행" :
                image = Shinhan;
                break;
            case "농협은행주식회사" :
                image = NH;
                break;
            case "수협은행" :
                image = SH;
                break;
            case "한국씨티은행" :
                image = CT;
                break;
            case "주식회사 케이뱅크" :
                image = K;
                break;
            case "주식회사 카카오뱅크" :
                image = Kakao;
                break;
        }
        return image;
    }
}