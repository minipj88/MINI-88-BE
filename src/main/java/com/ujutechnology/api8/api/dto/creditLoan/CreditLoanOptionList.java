package com.ujutechnology.api8.api.dto.creditLoan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditLoanOptionList {
    private String dcls_month; // 공시 제출월
    private String fin_co_no; // 금융회사코드
    private String fin_prdt_cd; // 금융상품명
    private String crdt_prdt_type; // 대출종류 코드
    private String crdt_lend_rate_type; // 금리구분 코드
    private String crdt_lend_rate_type_nm; // 금리구분
    private String crdt_grad_1;  // 900점 초과
    private String crdt_grad_4;  // 801~900점
    private String crdt_grad_5;  // 701~800점
    private String crdt_grad_6;  // 601~700점
    private String crdt_grad_10; // 501~600점
    private String crdt_grad_11; // 401~500점
    private String crdt_grad_12; // 301~400점
    private String crdt_grad_13; // 300점 이하
    private String crdt_grad_avg; // 평균 금리
}
