package com.ujutechnology.api8.api.dto.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ujutechnology.api8.biz.domain.Product;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class DepositBaseList {
    private String dcls_month; // 공시 제출월
    private String fin_co_no; // 금융회사 코드
    private String kor_co_nm; // 금융회사명
    private String fin_prdt_cd; // 금융상품 코드
    private String fin_prdt_nm; // 금융 상품명
    private String join_way; // 가입 방법
    private String mtrt_int; // 만기 후 이자율
    private String spcl_cnd; // 우대조건
    private String join_deny; // 가입제한) 1:제한없음 2:서민전용 3:일부제한
    private String join_member; // 가입대상
    private String etc_note; // 기타 유의사항
    private String max_limit; // 최고 한도
    private String dcls_strt_day; // 공시 시작일
    private String dcls_end_day; // 공시 종료일
    private String fin_co_subm_day; // 금융회사 제출일

    public Product ConvertToEntity() {
        return Product.builder()
                .Name(this.fin_prdt_nm)
                .Rate(this.mtrt_int)
                .build();
    }
}
