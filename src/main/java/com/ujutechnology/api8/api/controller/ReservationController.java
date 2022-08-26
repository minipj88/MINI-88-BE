package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.ReservationDto;
import com.ujutechnology.api8.api.dto.ResultDto;
import com.ujutechnology.api8.biz.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author kei
 * @since 2022-08-26
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResultDto<String> regist(ReservationDto reservationDto){
        log.debug("reservation >>>"+reservationDto.toString());

        reservationService.regist(reservationDto);
        ResultDto<String> resultDto = new ResultDto<>();
        resultDto.setResult(true);
        return resultDto;
    }
    @DeleteMapping("/reservation")
    public ResultDto<String> cancle(ReservationDto reservationDto){

        reservationService.cancle(reservationDto);
        ResultDto<String> resultDto = new ResultDto<>();
        resultDto.setResult(true);
        return resultDto;
    }

}
