package com.ujutechnology.api8.biz.service;

import com.ujutechnology.api8.api.dto.ReservationDto;
import com.ujutechnology.api8.biz.domain.Reservation;
import com.ujutechnology.api8.biz.repository.MemberRepository;
import com.ujutechnology.api8.biz.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kei
 * @since 2022-08-26
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    public void regist(ReservationDto reservationDto) {
        memberRepository.findByEmail(reservationDto.getEmail()).ifPresent(member->{
            Reservation reservation = Reservation.builder().memberId(member.getId()).productId(reservationDto.getProductId()).build();
            reservationRepository.save(reservation);
        });
    }

    public void cancle(ReservationDto reservationDto) {
        memberRepository.findByEmail(reservationDto.getEmail()).ifPresent(member->{
            reservationRepository.deleteByMemberIdAndProductId(member.getId(), reservationDto.getProductId());
        });
    }
}
