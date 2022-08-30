package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.api.dto.ReservationDto;
import com.ujutechnology.api8.biz.domain.Product;
import com.ujutechnology.api8.biz.domain.Reservation;
import com.ujutechnology.api8.biz.repository.CartRepository;
import com.ujutechnology.api8.biz.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kei
 * @since 2022-08-26
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
    private final CartRepository cartRepository;
    private final ReservationRepository reservationRepository;

    public void register(ReservationDto reservationDto) {
        Reservation reservation = Reservation.builder()
                .memberEmail(reservationDto.getEmail())
                .product(Product.builder().id(reservationDto.getProductId()).build())
                .build();
        cartRepository.deleteByMemberEmailAndProductId(reservationDto.getEmail(), reservationDto.getProductId());
        reservationRepository.save(reservation);
    }

    public void cancle(ReservationDto reservationDto) {
            reservationRepository.deleteByMemberEmailAndProductId(reservationDto.getEmail(), reservationDto.getProductId());
    }

    public List<Reservation> getReservationList(String email) {
        return reservationRepository.findByMemberEmail(email);
    }
}
