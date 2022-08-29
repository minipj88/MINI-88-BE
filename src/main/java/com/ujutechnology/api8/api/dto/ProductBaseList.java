package com.ujutechnology.api8.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ujutechnology.api8.biz.domain.Product;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)

public class ProductBaseList extends Product {
    private String finCoNo; // 금융회사 코드
    private String korCoNm; // 금융회사명
    private String finPrdtCd; // 금융상품 코드
    private String finPrdtNm; // 금융 상품명
    private String joinWay; // 가입 방법
    private String cbName; // CB 회사명
    private String crdtPrdtTypeNm; // 대출종류명
    private String crdtPrdtType; // 대출종류 코드
    private String loanInciExpn; // 대출 부대비용
    private String erlyRpayFee; // 중도 상환 수수료
    private String dlyRate; // 연체 이자율
    private String loanLmt; // 대출한도

}
