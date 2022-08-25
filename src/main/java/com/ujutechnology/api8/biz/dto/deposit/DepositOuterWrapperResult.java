package com.ujutechnology.api8.biz.dto.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositOuterWrapperResult {
        private String err_cd; // 응답코드
        private String err_msg; // 응답메시지
        private String total_count; // 총 상품건수
        private String max_page_no; // 총 페이지 건수
        private String now_page_no; // 현재 조회 페이지 번호

        private List<DepositBaseList> baseList;

/*        private String intr_rate_type; // 저축 금리 유형
        private String intr_rate_type_nm; // 저축 금리 유형명
        private String save_trm; // 저축 기간
        private String intr_rate; // 저축 금리
        private String intr_rate2; // 최고 우대금리{*/
}
