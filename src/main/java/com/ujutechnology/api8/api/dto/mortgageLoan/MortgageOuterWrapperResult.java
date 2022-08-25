package com.ujutechnology.api8.api.dto.mortgageLoan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MortgageOuterWrapperResult {
        private String err_cd; // 응답코드
        private String err_msg; // 응답메시지
        private String total_count; // 총 상품건수
        private String max_page_no; // 총 페이지 건수
        private String now_page_no; // 현재 조회 페이지 번호

        private List<MortgageBaseList> baseList;
        private List<MortgageOptionList> optionList;

}
