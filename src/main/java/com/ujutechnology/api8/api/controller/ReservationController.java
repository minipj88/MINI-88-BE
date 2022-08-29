package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.ReservationDto;
import com.ujutechnology.api8.biz.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void register(ReservationDto reservationDto){
        log.info("reservation >>>"+reservationDto.toString());

        reservationService.register(reservationDto);
    }
    @DeleteMapping("/reservation")
    public void cancle(ReservationDto reservationDto){
        log.info("reservation >>>"+reservationDto.toString());

        reservationService.cancle(reservationDto);
    }

}
