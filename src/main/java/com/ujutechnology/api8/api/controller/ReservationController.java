package com.ujutechnology.api8.api.controller;

import com.ujutechnology.api8.api.dto.ReservationDto;
import com.ujutechnology.api8.biz.domain.Reservation;
import com.ujutechnology.api8.biz.service.ReservationService;
import com.ujutechnology.api8.security.MemberAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public void register(@RequestBody ReservationDto reservationDto, @ApiIgnore HttpSession session){
        reservationDto.setEmail(
                ((MemberAuth)session.getAttribute("auth")).getEmail()
        );
        log.info("register>>> "+ reservationDto.toString());
        reservationService.register(reservationDto);
    }
    @DeleteMapping("/reservation")
    public void cancle(@RequestBody ReservationDto reservationDto, @ApiIgnore HttpSession session){
        reservationDto.setEmail(
                ((MemberAuth)session.getAttribute("auth")).getEmail()
        );
        log.info("cancle>>> "+ reservationDto.toString());
        reservationService.cancle(reservationDto);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservationList(String email) {
        log.info("getReservationList>>> "+email);
        return ResponseEntity.ok(reservationService.getReservationList(email));
    }
}
