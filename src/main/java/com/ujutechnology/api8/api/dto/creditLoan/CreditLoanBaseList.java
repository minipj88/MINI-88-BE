package com.ujutechnology.api8.api.dto.creditLoan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditLoanBaseList {
    private String dcls_month; // 공시 제출월
    private String fin_co_no; // 금융회사 코드
    private String kor_co_nm; // 금융회사명
    private String fin_prdt_cd; // 금융상품 코드
    private String fin_prdt_nm; // 금융 상품명
    private String join_way; // 가입 방법
    private String cb_name; // CB 회사명
    private String crdt_prdt_type_nm; // 대출종류명
    private String crdt_prdt_type; // 대출종류 코드
}
