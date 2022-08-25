package com.ujutechnology.api8.api.dto.rentHouseLoan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentHouseBaseList {
    private String dcls_month; // 공시 제출월
    private String fin_co_no; // 금융회사 코드
    private String kor_co_nm; // 금융회사명
    private String fin_prdt_cd; // 금융상품 코드
    private String fin_prdt_nm; // 금융 상품명
    private String join_way; // 가입 방법
    private String loan_inci_expn; // 대출 부대비용
    private String erly_rpay_fee; // 중도 상환 수수료
    private String dly_rate; // 연체 이자율
    private String loan_lmt; // 대출한도
}
