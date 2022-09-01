package com.ujutechnology.api8.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOptionList {
    private String crdtPrdtType; // 대출종류 코드
    private String crdtPrdtTypeNm;
    private String crdtLendRateType; // 금리구분 코드
    private String crdtLendRateTypeNm; // 금리구분
    private String crdtGradAvg; // 평균 금리
    private Double lendRateMin; // 대출금리_최저
    private Double lendRateMax; // 대출금리_최고
    private String crdtGrad_1; // 최저점
    private String crdtGrad_4;
    private String crdtGrad_5;
    private String crdtGrad_6;
    private String crdtGrad_10;
    private String crdtGrad_11;
    private String crdtGrad_12;
    private String crdtGrad_13;

}
