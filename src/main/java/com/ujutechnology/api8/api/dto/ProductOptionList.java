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
    private String lend_rate_min; // 대출금리_최저
    private String lend_rate_max; // 대출금리_최고

}
