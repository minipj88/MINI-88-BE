package com.ujutechnology.api8.api.dto.rentHouseLoan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentHouseOptionList {
    private String dcls_month; // 공시 제출월
    private String fin_co_no; // 금융회사 코드
    private String fin_prdt_cd; // 금융상품 코드
    private String rpay_type; // 대출상환유형 코드
    private String rpay_type_nm; // 대출상환유형
    private String lend_rate_type; // 대출금리유형 코드
    private String lend_rate_type_nm; // 대출금리유형
    private String lend_rate_min; // 대출금리_최저
    private String lend_rate_max; // 대출금리_최고
    private String lend_rate_avg; // 전월 취급 평균 금리
}
